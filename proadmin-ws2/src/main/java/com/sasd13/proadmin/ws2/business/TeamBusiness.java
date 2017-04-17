package com.sasd13.proadmin.ws2.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.error.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ITeamDAO;

public class TeamBusiness implements IBusiness<Team> {

	@Autowired
	private ITeamDAO dao;

	private Map<String, String[]> parameters;

	public TeamBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(Team team) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { team.getNumber() });

		if (!dao.read(parameters).isEmpty()) {
			throw new BusinessException("Team already exist");
		}
	}

	@Override
	public void verify(IUpdateWrapper<Team> updateWrapper) throws BusinessException {
		TeamUpdateWrapper teamUpdateWrapper = (TeamUpdateWrapper) updateWrapper;

		if (!teamUpdateWrapper.getNumber().equals(teamUpdateWrapper.getWrapped().getNumber())) {
			verify(teamUpdateWrapper.getWrapped());
		}
	}
}

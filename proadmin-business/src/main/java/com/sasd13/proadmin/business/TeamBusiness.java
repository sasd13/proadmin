package com.sasd13.proadmin.business;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.exception.BusinessException;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

public class TeamBusiness implements IBusiness<Team> {

	private Map<String, String[]> parameters;

	public TeamBusiness() {
		parameters = new HashMap<>();
	}

	@Override
	public void verify(DAO dao, Team team) throws BusinessException {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { team.getNumber() });

		if (dao.getSession(Team.class).select(parameters) != null) {
			throw new BusinessException("Team already exist");
		}
	}

	@Override
	public void verify(DAO dao, IUpdateWrapper<Team> updateWrapper) throws BusinessException {
		TeamUpdateWrapper teamUpdateWrapper = (TeamUpdateWrapper) updateWrapper;

		if (!teamUpdateWrapper.getNumber().equals(teamUpdateWrapper.getWrapped().getNumber())) {
			verify(dao, teamUpdateWrapper.getWrapped());
		}
	}
}

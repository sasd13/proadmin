package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.ITeamDAO;
import com.sasd13.proadmin.ws.service.ITeamService;

public class TeamService implements ITeamService {

	private ITeamDAO teamDAO;

	public TeamService(DAO dao) {
		teamDAO = (ITeamDAO) dao.getSession(ITeamDAO.class);
	}

	@Override
	public long create(ITeam iTeam) {
		return teamDAO.create(iTeam);
	}

	@Override
	public void update(TeamUpdateWrapper updateWrapper) {
		teamDAO.update(updateWrapper);
	}

	@Override
	public void delete(ITeam iTeam) {
		teamDAO.delete(iTeam);
	}

	@Override
	public List<ITeam> read(Map<String, String[]> parameters) {
		return teamDAO.read(parameters);
	}

	@Override
	public List<ITeam> readAll() {
		return teamDAO.readAll();
	}
}

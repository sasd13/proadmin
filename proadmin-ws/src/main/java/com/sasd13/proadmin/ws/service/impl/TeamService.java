package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.bean.update.TeamUpdate;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.ITeamDAO;
import com.sasd13.proadmin.ws.service.ITeamService;

public class TeamService implements ITeamService {

	private ITeamDAO teamDAO;

	public TeamService(DAO dao) {
		teamDAO = (ITeamDAO) dao.getSession(ITeamDAO.class);
	}

	@Override
	public long create(Team team) {
		return teamDAO.create(team);
	}

	@Override
	public void update(TeamUpdate updateWrapper) {
		teamDAO.update(updateWrapper);
	}

	@Override
	public void delete(Team team) {
		teamDAO.delete(team);
	}

	@Override
	public List<Team> read(Map<String, String[]> parameters) {
		return teamDAO.read(parameters);
	}

	@Override
	public List<Team> readAll() {
		return teamDAO.readAll();
	}
}

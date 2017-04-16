package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.ITeamDAO;
import com.sasd13.proadmin.service.ITeamService;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

public class TeamService implements ITeamService {

	private ITeamDAO session;

	public TeamService(DAO dao) {
		session = (ITeamDAO) dao.getSession(ITeamDAO.class);
	}

	@Override
	public long create(Team team) {
		return session.create(team);
	}

	@Override
	public void update(TeamUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(Team team) {
		session.delete(team);
	}

	@Override
	public List<Team> read(Map<String, String[]> parameters) {
		return session.read(parameters);
	}

	@Override
	public List<Team> readAll() {
		return session.readAll();
	}
}

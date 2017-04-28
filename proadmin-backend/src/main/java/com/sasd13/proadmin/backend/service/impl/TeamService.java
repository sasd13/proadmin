package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.dao.ITeamDAO;
import com.sasd13.proadmin.backend.model.Team;
import com.sasd13.proadmin.backend.service.ITeamService;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private ITeamDAO teamDAO;

	@Override
	public void create(Team team) {
		teamDAO.create(team);
	}

	@Override
	public void update(Team team) {
		teamDAO.update(team);
	}

	@Override
	public void delete(Team team) {
		teamDAO.delete(team);
	}

	@Override
	public List<Team> read(Map<String, String[]> parameters) {
		return teamDAO.read(parameters);
	}
}

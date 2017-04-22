package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.Team;
import com.sasd13.proadmin.backend.dao.ITeamDAO;
import com.sasd13.proadmin.backend.dao.dto.TeamDTO;
import com.sasd13.proadmin.backend.service.ITeamService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.TeamAdapterD2B;

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
		List<Team> list = new ArrayList<>();

		List<TeamDTO> dtos = teamDAO.read(parameters);
		TeamAdapterD2B adapter = new TeamAdapterD2B();

		for (TeamDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}

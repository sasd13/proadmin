package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.ITeamDAO;
import com.sasd13.proadmin.ws2.db.dto.TeamDTO;
import com.sasd13.proadmin.ws2.service.ITeamService;
import com.sasd13.proadmin.ws2.util.adapter.TeamDTOAdapter;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private ITeamDAO teamDAO;

	@Override
	public void create(Team team) {
		teamDAO.create(team);
	}

	@Override
	public void update(List<TeamUpdateWrapper> updateWrappers) {
		teamDAO.update(updateWrappers);
	}

	@Override
	public void delete(List<Team> teams) {
		teamDAO.delete(teams);
	}

	@Override
	public List<Team> read(Map<String, String[]> parameters) {
		List<Team> teams = new ArrayList<>();

		List<TeamDTO> dtos = teamDAO.read(parameters);
		TeamDTOAdapter adapter = new TeamDTOAdapter();

		for (TeamDTO dto : dtos) {
			teams.add(adapter.adapt(dto));
		}

		return teams;
	}
}

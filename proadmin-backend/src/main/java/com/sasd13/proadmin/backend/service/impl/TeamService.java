package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.ITeamDAO;
import com.sasd13.proadmin.backend.model.Team;
import com.sasd13.proadmin.backend.service.ITeamService;
import com.sasd13.proadmin.backend.util.adapter.itf.TeamITFAdapter;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeamService implements ITeamService {

	@Autowired
	private ITeamDAO teamDAO;

	private TeamITFAdapter adapter;

	public TeamService() {
		adapter = new TeamITFAdapter();
	}

	@Override
	public void create(TeamBean teamBean) {
		Team team = adapter.adaptI2M(teamBean);

		teamDAO.create(team);
	}

	@Override
	public void update(TeamBean teamBean) {
		Team team = adapter.adaptI2M(teamBean);

		teamDAO.update(team);
	}

	@Override
	public void delete(TeamBean teamBean) {
		Team team = adapter.adaptI2M(teamBean);

		teamDAO.delete(team);
	}

	@Override
	public List<TeamBean> read(Map<String, Object> criterias) {
		List<Team> teams = teamDAO.read(criterias);

		return adapter.adaptM2I(teams);
	}
}

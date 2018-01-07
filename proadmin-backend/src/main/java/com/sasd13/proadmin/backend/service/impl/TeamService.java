package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.ITeamDAO;
import com.sasd13.proadmin.backend.entity.Team;
import com.sasd13.proadmin.backend.service.ITeamService;
import com.sasd13.proadmin.backend.util.adapter.entity2itf.TeamAdapterM2I;
import com.sasd13.proadmin.backend.util.adapter.itf2entity.TeamAdapterI2M;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class TeamService implements ITeamService {

	@Autowired
	private ITeamDAO teamDAO;

	@Override
	public void create(TeamBean teamBean) {
		Team team = adaptI2M(teamBean);

		teamDAO.create(team);
	}

	private Team adaptI2M(TeamBean teamBean) {
		return new TeamAdapterI2M().adapt(teamBean);
	}

	@Override
	public void update(TeamBean teamBean) {
		Team team = adaptI2M(teamBean);

		teamDAO.update(team);
	}

	@Override
	public void delete(TeamBean teamBean) {
		Team team = adaptI2M(teamBean);

		teamDAO.delete(team);
	}

	@Override
	public List<TeamBean> read(Map<String, Object> criterias) {
		List<Team> teams = teamDAO.read(criterias);

		return adaptM2I(teams);
	}

	private List<TeamBean> adaptM2I(List<Team> teams) {
		List<TeamBean> list = new ArrayList<>();
		TeamAdapterM2I adapter = new TeamAdapterM2I();

		for (Team team : teams) {
			list.add(adapter.adapt(team));
		}

		return list;
	}
}

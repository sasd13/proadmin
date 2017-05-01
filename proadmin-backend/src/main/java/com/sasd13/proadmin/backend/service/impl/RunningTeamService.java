package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.dao.IRunningTeamDAO;
import com.sasd13.proadmin.backend.model.RunningTeam;
import com.sasd13.proadmin.backend.service.IRunningTeamService;

@Service
public class RunningTeamService implements IRunningTeamService {

	@Autowired
	private IRunningTeamDAO runningTeamDAO;

	@Override
	public void create(RunningTeam runningTeam) {
		runningTeamDAO.create(runningTeam);
	}

	@Override
	public void update(RunningTeam runningTeam) {
		runningTeamDAO.update(runningTeam);
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		runningTeamDAO.delete(runningTeam);
	}

	@Override
	public List<RunningTeam> read(Map<String, Object> criterias) {
		return runningTeamDAO.read(criterias);
	}
}

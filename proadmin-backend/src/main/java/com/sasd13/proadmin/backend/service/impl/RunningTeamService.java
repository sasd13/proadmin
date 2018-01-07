package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IRunningTeamDAO;
import com.sasd13.proadmin.backend.model.RunningTeam;
import com.sasd13.proadmin.backend.service.IRunningTeamService;
import com.sasd13.proadmin.backend.util.adapter.itf.RunningTeamITFAdapter;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RunningTeamService implements IRunningTeamService {

	@Autowired
	private IRunningTeamDAO runningTeamDAO;

	private RunningTeamITFAdapter adapter;

	public RunningTeamService() {
		adapter = new RunningTeamITFAdapter();
	}

	@Override
	public void create(RunningTeamBean runningTeamBean) {
		RunningTeam runningTeam = adapter.adaptI2M(runningTeamBean);

		runningTeamDAO.create(runningTeam);
	}

	@Override
	public void update(RunningTeamBean runningTeamBean) {
		RunningTeam runningTeam = adapter.adaptI2M(runningTeamBean);

		runningTeamDAO.update(runningTeam);
	}

	@Override
	public void delete(RunningTeamBean runningTeamBean) {
		RunningTeam runningTeam = adapter.adaptI2M(runningTeamBean);

		runningTeamDAO.delete(runningTeam);
	}

	@Override
	public List<RunningTeamBean> read(Map<String, Object> criterias) {
		List<RunningTeam> runningTeams = runningTeamDAO.read(criterias);

		return adapter.adaptM2I(runningTeams);
	}
}

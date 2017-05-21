package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IRunningTeamDAO;
import com.sasd13.proadmin.backend.model.RunningTeam;
import com.sasd13.proadmin.backend.service.IRunningTeamService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.RunningTeamAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.RunningTeamAdapterM2I;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class RunningTeamService implements IRunningTeamService {

	@Autowired
	private IRunningTeamDAO runningTeamDAO;

	@Override
	public void create(RunningTeamBean runningTeamBean) {
		RunningTeam runningTeam = adaptI2M(runningTeamBean);

		runningTeamDAO.create(runningTeam);
	}

	private RunningTeam adaptI2M(RunningTeamBean runningTeamBean) {
		return new RunningTeamAdapterI2M().adapt(runningTeamBean);
	}

	@Override
	public void update(RunningTeamBean runningTeamBean) {
		RunningTeam runningTeam = adaptI2M(runningTeamBean);

		runningTeamDAO.update(runningTeam);
	}

	@Override
	public void delete(RunningTeamBean runningTeamBean) {
		RunningTeam runningTeam = adaptI2M(runningTeamBean);

		runningTeamDAO.delete(runningTeam);
	}

	@Override
	public List<RunningTeamBean> read(Map<String, Object> criterias) {
		List<RunningTeam> runningTeams = runningTeamDAO.read(criterias);

		return adaptM2I(runningTeams);
	}

	private List<RunningTeamBean> adaptM2I(List<RunningTeam> runningTeams) {
		List<RunningTeamBean> list = new ArrayList<>();
		RunningTeamAdapterM2I adapter = new RunningTeamAdapterM2I();

		for (RunningTeam runningTeam : runningTeams) {
			list.add(adapter.adapt(runningTeam));
		}

		return list;
	}
}

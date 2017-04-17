package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws.dao.jdbc.RunningTeamDeepReader;
import com.sasd13.proadmin.ws.service.IRunningTeamService;

public class RunningTeamService implements IRunningTeamService {

	private IRunningTeamDAO runningTeamDAO;
	private RunningTeamDeepReader runningTeamDeepReader;

	public RunningTeamService(DAO dao) {
		runningTeamDAO = (IRunningTeamDAO) dao.getSession(IRunningTeamDAO.class);
		runningTeamDeepReader = (RunningTeamDeepReader) dao.getDeepReader(RunningTeamDeepReader.class);
	}

	@Override
	public long create(RunningTeam runningTeam) {
		return runningTeamDAO.create(runningTeam);
	}

	@Override
	public void update(RunningTeamUpdateWrapper updateWrapper) {
		runningTeamDAO.update(updateWrapper);
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		runningTeamDAO.delete(runningTeam);
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) {
		return runningTeamDeepReader.read(parameters);
	}

	@Override
	public List<RunningTeam> readAll() {
		return runningTeamDeepReader.readAll();
	}
}

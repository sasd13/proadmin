package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IRunningTeam;
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
	public long create(IRunningTeam iRunningTeam) {
		return runningTeamDAO.create(iRunningTeam);
	}

	@Override
	public void update(RunningTeamUpdate updateWrapper) {
		runningTeamDAO.update(updateWrapper);
	}

	@Override
	public void delete(IRunningTeam iRunningTeam) {
		runningTeamDAO.delete(iRunningTeam);
	}

	@Override
	public List<IRunningTeam> read(Map<String, String[]> parameters) {
		return runningTeamDeepReader.read(parameters);
	}

	@Override
	public List<IRunningTeam> readAll() {
		return runningTeamDeepReader.readAll();
	}
}

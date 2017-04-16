package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.IRunningTeamDAO;
import com.sasd13.proadmin.dao.RunningTeamDeepReader;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

public class RunningTeamService implements IRunningTeamService {

	private IRunningTeamDAO session;
	private RunningTeamDeepReader deepReader;

	public RunningTeamService(DAO dao) {
		session = (IRunningTeamDAO) dao.getSession(IRunningTeamDAO.class);
		deepReader = (RunningTeamDeepReader) dao.getDeepReader(RunningTeamDeepReader.class);
	}

	@Override
	public long create(RunningTeam runningTeam) {
		return session.create(runningTeam);
	}

	@Override
	public void update(RunningTeamUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		session.delete(runningTeam);
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) {
		return deepReader.read(parameters);
	}

	@Override
	public List<RunningTeam> readAll() {
		return deepReader.readAll();
	}
}

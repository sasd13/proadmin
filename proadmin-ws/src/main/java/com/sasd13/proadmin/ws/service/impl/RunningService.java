package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IRunningDAO;
import com.sasd13.proadmin.ws.dao.jdbc.RunningDeepReader;
import com.sasd13.proadmin.ws.service.IRunningService;

public class RunningService implements IRunningService {

	private IRunningDAO runningDAO;
	private RunningDeepReader runningDeepReader;

	public RunningService(DAO dao) {
		runningDAO = (IRunningDAO) dao.getSession(IRunningDAO.class);
		runningDeepReader = (RunningDeepReader) dao.getDeepReader(RunningDeepReader.class);
	}

	@Override
	public long create(IRunning iRunning) {
		return runningDAO.create(iRunning);
	}

	@Override
	public void update(RunningUpdate updateWrapper) {
		runningDAO.update(updateWrapper);
	}

	@Override
	public void delete(IRunning iRunning) {
		runningDAO.delete(iRunning);
	}

	@Override
	public List<IRunning> read(Map<String, String[]> parameters) {
		return runningDeepReader.read(parameters);
	}

	@Override
	public List<IRunning> readAll() {
		return runningDeepReader.readAll();
	}
}

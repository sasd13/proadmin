package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.update.RunningUpdate;
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
	public long create(Running running) {
		return runningDAO.create(running);
	}

	@Override
	public void update(RunningUpdate runningUpdate) {
		runningDAO.update(runningUpdate);
	}

	@Override
	public void delete(Running running) {
		runningDAO.delete(running);
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		return runningDeepReader.read(parameters);
	}

	@Override
	public List<Running> readAll() {
		return runningDeepReader.readAll();
	}
}

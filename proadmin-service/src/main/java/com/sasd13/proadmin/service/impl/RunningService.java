package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.IRunningDAO;
import com.sasd13.proadmin.dao.RunningDeepReader;
import com.sasd13.proadmin.service.IRunningService;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

public class RunningService implements IRunningService {

	private IRunningDAO session;
	private RunningDeepReader deepReader;

	public RunningService(DAO dao) {
		session = (IRunningDAO) dao.getSession(IRunningDAO.class);
		deepReader = (RunningDeepReader) dao.getDeepReader(RunningDeepReader.class);
	}

	@Override
	public long create(Running running) {
		return session.create(running);
	}

	@Override
	public void update(RunningUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(Running running) {
		session.delete(running);
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		return deepReader.read(parameters);
	}

	@Override
	public List<Running> readAll() {
		return deepReader.readAll();
	}
}

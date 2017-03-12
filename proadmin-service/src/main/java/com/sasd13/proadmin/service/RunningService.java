package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.DAO;

public class RunningService extends Service<Running> {

	private static final Logger LOGGER = Logger.getLogger(RunningService.class);

	public RunningService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Running running) {
		try {
			getSession(Running.class).insert(running);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Running> updateWrapper) {
		try {
			getSession(Running.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Running running) {
		try {
			getSession(Running.class).delete(running);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		List<Running> runnings = new ArrayList<>();

		try {
			runnings = getSession(Running.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}

	@Override
	public List<Running> readAll() {
		List<Running> runnings = new ArrayList<>();

		try {
			runnings = getSession(Running.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}

	@Override
	public List<Running> deepRead(Map<String, String[]> parameters) {
		List<Running> runnings = new ArrayList<>();

		try {
			runnings = getDeepReader(Running.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}

	@Override
	public List<Running> deepReadAll() {
		List<Running> runnings = new ArrayList<>();

		try {
			runnings = getDeepReader(Running.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}
}

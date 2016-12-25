package com.sasd13.proadmin.ws.service.running;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class RunningService extends AbstractService<Running> {

	private static final Logger LOGGER = Logger.getLogger(RunningService.class);

	public RunningService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Running running) {
		LOGGER.info("create : year = " + running.getYear() + ", projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		try {
			currentConnection().getSession(Running.class).insert(running);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Running> updateWrapper) {
		LOGGER.info("update : year = " + ((IRunningUpdateWrapper) updateWrapper).getYear() + ", projectCode=" + ((IRunningUpdateWrapper) updateWrapper).getProjectCode() + ", teacherNumber=" + ((IRunningUpdateWrapper) updateWrapper).getTeacherNumber());

		try {
			currentConnection().getSession(Running.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Running running) {
		LOGGER.info("delete : year = " + running.getYear() + ", projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		try {
			currentConnection().getSession(Running.class).delete(running);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Running> runnings = new ArrayList<>();

		try {
			runnings = currentConnection().getSession(Running.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}

	@Override
	public List<Running> readAll() {
		LOGGER.info("readAll");

		List<Running> runnings = new ArrayList<>();

		try {
			runnings = currentConnection().getSession(Running.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}

	@Override
	public List<Running> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<Running> runnings = new ArrayList<>();

		try {
			runnings = currentConnection().getDeepReader(Running.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}

	@Override
	public List<Running> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<Running> runnings = new ArrayList<>();

		try {
			runnings = currentConnection().getDeepReader(Running.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runnings;
	}
}

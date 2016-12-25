package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class RunningService extends AbstractService<Running> {

	private static final Logger LOGGER = Logger.getLogger(RunningService.class);

	public RunningService() {
		super();
	}

	@Override
	public void create(List<Running> runnings) {
		try {
			dao.open();

			ISession<Running> session = dao.getSession(Running.class);

			for (Running running : runnings) {
				LOGGER.info("create : year = " + running.getYear() + ", projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());
				session.insert(running);
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}

	@Override
	public void update(List<IUpdateWrapper<Running>> updateWrappers) {
		try {
			dao.open();

			ISession<Running> session = dao.getSession(Running.class);
			IRunningUpdateWrapper runningUpdateWrapper;

			for (IUpdateWrapper<Running> updateWrapper : updateWrappers) {
				runningUpdateWrapper = (IRunningUpdateWrapper) updateWrapper;

				LOGGER.info("update : year = " + runningUpdateWrapper.getYear() + ", projectCode=" + runningUpdateWrapper.getProjectCode() + ", teacherNumber=" + runningUpdateWrapper.getTeacherNumber());
				session.update(runningUpdateWrapper);
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}

	@Override
	public void delete(List<Running> runnings) {
		try {
			dao.open();

			ISession<Running> session = dao.getSession(Running.class);

			for (Running running : runnings) {
				LOGGER.info("delete : year = " + running.getYear() + ", projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());
				session.delete(running);
			}
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Running> runnings = new ArrayList<>();

		try {
			dao.open();

			runnings = dao.getSession(Running.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return runnings;
	}

	@Override
	public List<Running> readAll() {
		LOGGER.info("readAll");

		List<Running> runnings = new ArrayList<>();

		try {
			dao.open();

			runnings = dao.getSession(Running.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return runnings;
	}

	@Override
	public List<Running> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<Running> runnings = new ArrayList<>();

		try {
			dao.open();

			runnings = dao.getDeepReader(Running.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return runnings;
	}

	@Override
	public List<Running> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<Running> runnings = new ArrayList<>();

		try {
			dao.open();

			runnings = dao.getDeepReader(Running.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOGGER.warn(e);
			}
		}

		return runnings;
	}
}

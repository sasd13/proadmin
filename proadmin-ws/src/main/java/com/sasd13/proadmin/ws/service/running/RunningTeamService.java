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
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class RunningTeamService extends AbstractService<RunningTeam> {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamService.class);

	public RunningTeamService() {
		super();
	}

	@Override
	public void create(List<RunningTeam> runningTeams) {
		try {
			dao.open();

			ISession<RunningTeam> session = dao.getSession(RunningTeam.class);

			for (RunningTeam runningTeam : runningTeams) {
				LOGGER.info("create : year = " + runningTeam.getRunning().getYear() + ", projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());
				session.insert(runningTeam);
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
	public void update(List<IUpdateWrapper<RunningTeam>> updateWrappers) {
		try {
			dao.open();

			ISession<RunningTeam> session = dao.getSession(RunningTeam.class);
			IRunningTeamUpdateWrapper runningTeamUpdateWrapper;

			for (IUpdateWrapper<RunningTeam> updateWrapper : updateWrappers) {
				runningTeamUpdateWrapper = (IRunningTeamUpdateWrapper) updateWrapper;

				LOGGER.info("update : year = " + runningTeamUpdateWrapper.getRunningYear() + ", projectCode=" + runningTeamUpdateWrapper.getProjectCode() + ", teacherNumber=" + runningTeamUpdateWrapper.getTeacherNumber() + ", teamNumber=" + runningTeamUpdateWrapper.getTeamNumber() + ", academicLevel=" + runningTeamUpdateWrapper.getAcademicLevelCode());
				session.update(runningTeamUpdateWrapper);
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
	public void delete(List<RunningTeam> runningTeams) {
		try {
			dao.open();

			ISession<RunningTeam> session = dao.getSession(RunningTeam.class);

			for (RunningTeam runningTeam : runningTeams) {
				LOGGER.info("delete : year = " + runningTeam.getRunning().getYear() + ", projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());
				session.delete(runningTeam);
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
	public List<RunningTeam> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			dao.open();

			runningTeams = dao.getSession(RunningTeam.class).select(parameters);
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

		return runningTeams;
	}

	@Override
	public List<RunningTeam> readAll() {
		LOGGER.info("readAll");

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			dao.open();

			runningTeams = dao.getSession(RunningTeam.class).selectAll();
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

		return runningTeams;
	}

	@Override
	public List<RunningTeam> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			dao.open();

			runningTeams = dao.getDeepReader(RunningTeam.class).select(parameters);
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

		return runningTeams;
	}

	@Override
	public List<RunningTeam> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			dao.open();

			runningTeams = dao.getDeepReader(RunningTeam.class).selectAll();
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

		return runningTeams;
	}
}

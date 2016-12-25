package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class RunningTeamReadService implements IReadService<RunningTeam>, IDeepReadService<RunningTeam> {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamReadService.class);

	private DAO dao;

	public RunningTeamReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) throws ServiceException {
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
	public List<RunningTeam> readAll() throws ServiceException {
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
	public List<RunningTeam> deepRead(Map<String, String[]> parameters) throws ServiceException {
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
	public List<RunningTeam> deepReadAll() throws ServiceException {
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

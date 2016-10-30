package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class RunningTeamReadService implements IReadService<RunningTeam> {

	private static final Logger LOG = Logger.getLogger(RunningTeamReadService.class);

	private DAO dao;

	public RunningTeamReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) throws WSException {
		LOG.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			dao.open();

			runningTeams = dao.getEntityDAO(RunningTeam.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("read failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> readAll() throws WSException {
		LOG.info("readAll");

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			dao.open();

			runningTeams = dao.getEntityDAO(RunningTeam.class).selectAll();
		} catch (DAOException e) {
			LOG.error("readAll failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> deepRead(Map<String, String[]> parameters) throws WSException {
		LOG.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			dao.open();

			runningTeams = dao.getDeepReader(RunningTeam.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("deepRead failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> deepReadAll() throws WSException {
		LOG.info("deepReadAll");

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			dao.open();

			runningTeams = dao.getDeepReader(RunningTeam.class).selectAll();
		} catch (DAOException e) {
			LOG.error("deepReadAll failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return runningTeams;
	}
}

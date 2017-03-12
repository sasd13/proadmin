package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.DAO;

public class RunningTeamService extends Service<RunningTeam> {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamService.class);

	public RunningTeamService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(RunningTeam runningTeam) {
		try {
			getSession(RunningTeam.class).insert(runningTeam);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<RunningTeam> updateWrapper) {
		try {
			getSession(RunningTeam.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		try {
			getSession(RunningTeam.class).delete(runningTeam);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) {
		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			runningTeams = getSession(RunningTeam.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> readAll() {
		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			runningTeams = getSession(RunningTeam.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> deepRead(Map<String, String[]> parameters) {
		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			runningTeams = getDeepReader(RunningTeam.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> deepReadAll() {
		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			runningTeams = getDeepReader(RunningTeam.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}
}

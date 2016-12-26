package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;

public class RunningTeamService extends Service<RunningTeam> {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamService.class);

	public RunningTeamService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(RunningTeam runningTeam) {
		LOGGER.info("create : year = " + runningTeam.getRunning().getYear() + ", projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());

		try {
			currentConnection().getSession(RunningTeam.class).insert(runningTeam);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<RunningTeam> updateWrapper) {
		LOGGER.info("update : year = " + ((IRunningTeamUpdateWrapper) updateWrapper).getRunningYear() + ", projectCode=" + ((IRunningTeamUpdateWrapper) updateWrapper).getProjectCode() + ", teacherNumber=" + ((IRunningTeamUpdateWrapper) updateWrapper).getTeacherNumber() + ", teamNumber=" + ((IRunningTeamUpdateWrapper) updateWrapper).getTeamNumber() + ", academicLevel=" + ((IRunningTeamUpdateWrapper) updateWrapper).getAcademicLevelCode());

		try {
			currentConnection().getSession(RunningTeam.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		LOGGER.info("delete : year = " + runningTeam.getRunning().getYear() + ", projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());

		try {
			currentConnection().getSession(RunningTeam.class).delete(runningTeam);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<RunningTeam> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			runningTeams = currentConnection().getSession(RunningTeam.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> readAll() {
		LOGGER.info("readAll");

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			runningTeams = currentConnection().getSession(RunningTeam.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			runningTeams = currentConnection().getDeepReader(RunningTeam.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<RunningTeam> runningTeams = new ArrayList<>();

		try {
			runningTeams = currentConnection().getDeepReader(RunningTeam.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}
}

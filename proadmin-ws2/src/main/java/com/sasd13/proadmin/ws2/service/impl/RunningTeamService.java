package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws2.service.IService;

public class RunningTeamService implements IService<RunningTeam> {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamService.class);

	@Autowired
	private IRunningTeamDAO dao;

	@Override
	public void create(RunningTeam runningTeam) {
		LOGGER.info("create : year=" + runningTeam.getRunning().getYear() + ", projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());

		try {
			dao.insert(runningTeam);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<RunningTeam> updateWrapper) {
		LOGGER.info("update : year=" + ((IRunningTeamUpdateWrapper) updateWrapper).getRunningYear() + ", projectCode=" + ((IRunningTeamUpdateWrapper) updateWrapper).getProjectCode() + ", teacherNumber=" + ((IRunningTeamUpdateWrapper) updateWrapper).getTeacherNumber() + ", teamNumber=" + ((IRunningTeamUpdateWrapper) updateWrapper).getTeamNumber() + ", academicLevel=" + ((IRunningTeamUpdateWrapper) updateWrapper).getAcademicLevelCode());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		LOGGER.info("delete : year=" + runningTeam.getRunning().getYear() + ", projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());

		try {
			dao.delete(runningTeam);
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
			runningTeams = dao.select(parameters);
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
			runningTeams = dao.selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return runningTeams;
	}
}

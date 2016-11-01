package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class RunningTeamManageService implements IManageService<RunningTeam> {

	private static final Logger LOG = Logger.getLogger(RunningTeamManageService.class);

	private DAO dao;

	public RunningTeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(RunningTeam[] runningTeams) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<RunningTeam> runningTeamDAO = dao.getEntityDAO(RunningTeam.class);

			for (RunningTeam runningTeam : runningTeams) {
				LOG.info("create : projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());
				runningTeamDAO.insert(runningTeam);
			}
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(RunningTeam[] runningTeams) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<RunningTeam> runningTeamDAO = dao.getEntityDAO(RunningTeam.class);

			for (RunningTeam runningTeam : runningTeams) {
				LOG.info("update : projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());
				runningTeamDAO.update(runningTeam);
			}
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(RunningTeam[] runningTeams) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<RunningTeam> runningTeamDAO = dao.getEntityDAO(RunningTeam.class);

			for (RunningTeam runningTeam : runningTeams) {
				LOG.info("delete : projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());
				runningTeamDAO.delete(runningTeam);
			}
		} catch (DAOException e) {
			LOG.error(e);
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}

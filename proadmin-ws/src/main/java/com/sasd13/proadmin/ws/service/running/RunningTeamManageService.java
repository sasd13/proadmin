package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class RunningTeamManageService implements IManageService<RunningTeam> {

	private static final Logger LOGGER = Logger.getLogger(RunningTeamManageService.class);

	private DAO dao;

	public RunningTeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<RunningTeam> runningTeams) throws ServiceException {
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
	public void update(List<IUpdateWrapper<RunningTeam>> updateWrappers) throws ServiceException {
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
	public void delete(List<RunningTeam> runningTeams) throws ServiceException {
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
}

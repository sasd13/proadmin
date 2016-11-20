package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class RunningManageService implements IManageService<Running> {

	private static final Logger LOGGER = Logger.getLogger(RunningManageService.class);

	private DAO dao;

	public RunningManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Running> runnings) throws ServiceException {
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
	public void update(List<IUpdateWrapper<Running>> updateWrappers) throws ServiceException {
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
	public void delete(List<Running> runnings) throws ServiceException {
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
}

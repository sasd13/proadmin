package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class RunningManageService implements IManageService<Running> {

	private static final Logger LOG = Logger.getLogger(RunningManageService.class);

	private DAO dao;

	public RunningManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Running> runnings) throws ServiceException {
		try {
			dao.open();

			IManager<Running> runningDAO = dao.getSession(Running.class);

			for (Running running : runnings) {
				LOG.info("create : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());
				runningDAO.insert(running);
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
	public void update(List<Running> runnings) throws ServiceException {
		try {
			dao.open();

			IManager<Running> runningDAO = dao.getSession(Running.class);

			for (Running running : runnings) {
				LOG.info("update : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());
				runningDAO.update(running);
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
	public void delete(List<Running> runnings) throws ServiceException {
		try {
			dao.open();

			IManager<Running> runningDAO = dao.getSession(Running.class);

			for (Running running : runnings) {
				LOG.info("delete : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());
				runningDAO.delete(running);
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

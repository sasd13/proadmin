package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IEntityDAO;
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
	public void create(Running[] runnings) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Running> runningDAO = dao.getEntityDAO(Running.class);

			for (Running running : runnings) {
				LOG.info("create : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

				long id = runningDAO.insert(running);

				LOG.info("created with id=" + id);
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
	public void update(Running[] runnings) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Running> runningDAO = dao.getEntityDAO(Running.class);

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
	public void delete(Running[] runnings) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Running> runningDAO = dao.getEntityDAO(Running.class);

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

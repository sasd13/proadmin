package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class RunningManageService implements IManageService<Running> {

	private static final Logger LOG = Logger.getLogger(RunningManageService.class);

	private DAO dao;

	public RunningManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Running running) throws WSException {
		LOG.info("RunningManageService --> create : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Running.class).insert(running);
		} catch (DAOException e) {
			LOG.error("RunningManageService --> create failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Running running) throws WSException {
		LOG.info("RunningManageService --> update : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Running.class).update(running);
		} catch (DAOException e) {
			LOG.error("RunningManageService --> update failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(Running running) throws WSException {
		LOG.info("RunningManageService --> delete : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Running.class).update(running);
		} catch (DAOException e) {
			LOG.error("RunningManageService --> delete failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}

package com.sasd13.proadmin.ws.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class AcademicLevelManageService implements IManageService<AcademicLevel> {

	private static final Logger LOG = Logger.getLogger(AcademicLevelManageService.class);

	private DAO dao;

	public AcademicLevelManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<AcademicLevel> academicLevels) throws ServiceException {
		try {
			dao.open();

			ISession<AcademicLevel> academicLevelDAO = dao.getSession(AcademicLevel.class);

			for (AcademicLevel academicLevel : academicLevels) {
				LOG.info("create : code=" + academicLevel.getCode());
				academicLevelDAO.insert(academicLevel);
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
	public void update(List<AcademicLevel> academicLevels) throws ServiceException {
		LOG.info("update unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void delete(List<AcademicLevel> academicLevels) throws ServiceException {
		try {
			dao.open();

			ISession<AcademicLevel> academicLevelDAO = dao.getSession(AcademicLevel.class);

			for (AcademicLevel academicLevel : academicLevels) {
				LOG.info("delete : code=" + academicLevel.getCode());
				academicLevelDAO.delete(academicLevel);
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

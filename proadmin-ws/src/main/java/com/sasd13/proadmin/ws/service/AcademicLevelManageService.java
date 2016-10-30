package com.sasd13.proadmin.ws.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
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
	public void create(AcademicLevel academicLevel) throws WSException {
		LOG.info("create : code=" + academicLevel.getCode());

		try {
			dao.open();
			dao.getEntityDAO(AcademicLevel.class).insert(academicLevel);
		} catch (DAOException e) {
			LOG.error("create failed");
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
	public void update(AcademicLevel academicLevel) throws WSException {
		LOG.info("update unavailable");
		throw new WSException("Service unavailable");
	}

	@Override
	public void delete(AcademicLevel academicLevel) throws WSException {
		LOG.info("delete : code=" + academicLevel.getCode());

		try {
			dao.open();
			dao.getEntityDAO(AcademicLevel.class).delete(academicLevel);
		} catch (DAOException e) {
			LOG.error("delete failed");
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

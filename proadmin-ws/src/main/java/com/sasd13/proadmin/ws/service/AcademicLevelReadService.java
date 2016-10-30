package com.sasd13.proadmin.ws.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class AcademicLevelReadService implements IReadService<AcademicLevel> {

	private static final Logger LOG = Logger.getLogger(AcademicLevelReadService.class);

	private DAO dao;

	public AcademicLevelReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<AcademicLevel> read(Map<String, String[]> parameters) throws WSException {
		LOG.info("read");

		List<AcademicLevel> academicLevels = new ArrayList<>();

		try {
			dao.open();

			academicLevels = dao.getEntityDAO(AcademicLevel.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("read failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return academicLevels;
	}

	@Override
	public List<AcademicLevel> readAll() throws WSException {
		LOG.info("readAll");

		List<AcademicLevel> academicLevels = new ArrayList<>();

		try {
			dao.open();

			academicLevels = dao.getEntityDAO(AcademicLevel.class).selectAll();
		} catch (DAOException e) {
			LOG.error("readAll failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return academicLevels;
	}

	@Override
	public List<AcademicLevel> deepRead(Map<String, String[]> parameters) throws WSException {
		LOG.info("deepRead unavailable");
		throw new WSException("Service unavailable");
	}

	@Override
	public List<AcademicLevel> deepReadAll() throws WSException {
		LOG.info("deepReadAll unavailable");
		throw new WSException("Service unavailable");
	}
}

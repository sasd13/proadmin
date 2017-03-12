package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.DAO;

public class AcademicLevelService extends Service<AcademicLevel> {

	private static final Logger LOGGER = Logger.getLogger(AcademicLevelService.class);

	public AcademicLevelService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(AcademicLevel academicLevel) {
		try {
			getSession(AcademicLevel.class).insert(academicLevel);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<AcademicLevel> updateWrapper) {
		try {
			getSession(AcademicLevel.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(AcademicLevel academicLevel) {
		try {
			getSession(AcademicLevel.class).delete(academicLevel);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<AcademicLevel> read(Map<String, String[]> parameters) {
		List<AcademicLevel> academicLevels = new ArrayList<>();

		try {
			academicLevels = getSession(AcademicLevel.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return academicLevels;
	}

	@Override
	public List<AcademicLevel> readAll() {
		List<AcademicLevel> academicLevels = new ArrayList<>();

		try {
			academicLevels = getSession(AcademicLevel.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return academicLevels;
	}

	@Override
	public List<AcademicLevel> deepRead(Map<String, String[]> parameters) {
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<AcademicLevel> deepReadAll() {
		throw new ServiceException("Service unavailable");
	}
}

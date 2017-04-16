package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.DAO;

public class AcademicLevelService extends Service<AcademicLevel> {

	private static final Logger LOGGER = Logger.getLogger(AcademicLevelService.class);

	public AcademicLevelService(DAO dao) {
		super(dao);
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
		return read(parameters);
	}

	@Override
	public List<AcademicLevel> deepReadAll() {
		return readAll();
	}
}

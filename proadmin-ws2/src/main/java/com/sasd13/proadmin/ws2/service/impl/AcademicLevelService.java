package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.wrapper.update.IAcademicLevelUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws2.service.IService;

public class AcademicLevelService implements IService<AcademicLevel> {

	private static final Logger LOGGER = Logger.getLogger(AcademicLevel.class);

	@Autowired
	private IAcademicLevelDAO dao;

	@Override
	public void create(AcademicLevel academicLevel) {
		LOGGER.info("create : code=" + academicLevel.getCode());

		try {
			dao.insert(academicLevel);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<AcademicLevel> updateWrapper) {
		LOGGER.info("update : code=" + ((IAcademicLevelUpdateWrapper) updateWrapper).getCode());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(AcademicLevel academicLevel) {
		LOGGER.info("delete : code=" + academicLevel.getCode());

		try {
			dao.delete(academicLevel);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<AcademicLevel> read(Map<String, String[]> parameters) {
		LOGGER.info("read");

		List<AcademicLevel> academicLevels = new ArrayList<>();

		try {
			academicLevels = dao.select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return academicLevels;
	}

	@Override
	public List<AcademicLevel> readAll() {
		LOGGER.info("readAll");

		List<AcademicLevel> academicLevels = new ArrayList<>();

		try {
			academicLevels = dao.selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return academicLevels;
	}
}

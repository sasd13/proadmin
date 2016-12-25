package com.sasd13.proadmin.ws.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.IAcademicLevelUpdateWrapper;

public class AcademicLevelService extends AbstractService<AcademicLevel> {

	private static final Logger LOGGER = Logger.getLogger(AcademicLevelService.class);

	private DAO dao;

	public AcademicLevelService() {
		super();
	}

	@Override
	public void create(List<AcademicLevel> academicLevels) {
		try {
			dao.open();

			ISession<AcademicLevel> session = dao.getSession(AcademicLevel.class);

			for (AcademicLevel academicLevel : academicLevels) {
				LOGGER.info("create : code=" + academicLevel.getCode());
				session.insert(academicLevel);
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
	public void update(List<IUpdateWrapper<AcademicLevel>> updateWrappers) {
		try {
			dao.open();

			ISession<AcademicLevel> session = dao.getSession(AcademicLevel.class);
			IAcademicLevelUpdateWrapper academicLevelUpdateWrapper;

			for (IUpdateWrapper<AcademicLevel> updateWrapper : updateWrappers) {
				academicLevelUpdateWrapper = (IAcademicLevelUpdateWrapper) updateWrapper;

				LOGGER.info("update : code=" + academicLevelUpdateWrapper.getCode());
				session.update(academicLevelUpdateWrapper);
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
	public void delete(List<AcademicLevel> academicLevels) {
		try {
			dao.open();

			ISession<AcademicLevel> session = dao.getSession(AcademicLevel.class);

			for (AcademicLevel academicLevel : academicLevels) {
				LOGGER.info("delete : code=" + academicLevel.getCode());
				session.delete(academicLevel);
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
	public List<AcademicLevel> read(Map<String, String[]> parameters) {
		LOGGER.info("read");

		List<AcademicLevel> academicLevels = new ArrayList<>();

		try {
			dao.open();

			academicLevels = dao.getSession(AcademicLevel.class).select(parameters);
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

		return academicLevels;
	}

	@Override
	public List<AcademicLevel> readAll() {
		LOGGER.info("readAll");

		List<AcademicLevel> academicLevels = new ArrayList<>();

		try {
			dao.open();

			academicLevels = dao.getSession(AcademicLevel.class).selectAll();
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

		return academicLevels;
	}

	@Override
	public List<AcademicLevel> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<AcademicLevel> deepReadAll() {
		LOGGER.info("deepReadAll unavailable");
		throw new ServiceException("Service unavailable");
	}
}

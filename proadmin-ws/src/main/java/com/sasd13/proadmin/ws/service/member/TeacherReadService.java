package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class TeacherReadService implements IReadService<Teacher>, IDeepReadService<Teacher> {

	private static final Logger LOGGER = Logger.getLogger(TeacherReadService.class);

	private DAO dao;

	public TeacherReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Teacher> teachers = new ArrayList<>();

		try {
			dao.open();

			teachers = dao.getSession(Teacher.class).select(parameters);
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

		return teachers;
	}

	@Override
	public List<Teacher> readAll() throws ServiceException {
		LOGGER.info("readAll");

		List<Teacher> teachers = new ArrayList<>();

		try {
			dao.open();

			teachers = dao.getSession(Teacher.class).selectAll();
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

		return teachers;
	}

	@Override
	public List<Teacher> deepRead(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Teacher> deepReadAll() throws ServiceException {
		LOGGER.info("deepReadAll unavailable");
		throw new ServiceException("Service unavailable");
	}
}

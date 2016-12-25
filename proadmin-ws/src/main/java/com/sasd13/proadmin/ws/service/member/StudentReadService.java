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
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class StudentReadService implements IReadService<Student>, IDeepReadService<Student> {

	private static final Logger LOGGER = Logger.getLogger(StudentReadService.class);

	private DAO dao;

	public StudentReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Student> read(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Student> students = new ArrayList<>();

		try {
			dao.open();

			students = dao.getSession(Student.class).select(parameters);
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

		return students;
	}

	@Override
	public List<Student> readAll() throws ServiceException {
		LOGGER.info("readAll");

		List<Student> students = new ArrayList<>();

		try {
			dao.open();

			students = dao.getSession(Student.class).selectAll();
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

		return students;
	}

	@Override
	public List<Student> deepRead(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Student> deepReadAll() throws ServiceException {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}
}

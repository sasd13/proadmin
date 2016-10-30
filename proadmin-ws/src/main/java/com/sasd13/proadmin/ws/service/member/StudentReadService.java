package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class StudentReadService implements IReadService<Student> {

	private static final Logger LOG = Logger.getLogger(StudentReadService.class);

	private DAO dao;

	public StudentReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Student> read(Map<String, String[]> parameters) throws ServiceException {
		LOG.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Student> students = new ArrayList<>();

		try {
			dao.open();

			students = dao.getEntityDAO(Student.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("read failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return students;
	}

	@Override
	public List<Student> readAll() throws ServiceException {
		LOG.info("readAll");

		List<Student> students = new ArrayList<>();

		try {
			dao.open();

			students = dao.getEntityDAO(Student.class).selectAll();
		} catch (DAOException e) {
			LOG.error("readAll failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return students;
	}

	@Override
	public List<Student> deepRead(Map<String, String[]> parameters) throws ServiceException {
		LOG.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Student> deepReadAll() throws ServiceException {
		LOG.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}
}

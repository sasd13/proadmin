package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class TeacherReadService implements IReadService<Teacher> {

	private static final Logger LOG = Logger.getLogger(TeacherReadService.class);

	private DAO dao;

	public TeacherReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) throws WSException {
		LOG.info("TeacherReadService --> read : parameters=" + URLQueryUtils.toString(parameters));

		List<Teacher> teachers = new ArrayList<>();

		try {
			dao.open();

			teachers = dao.getEntityDAO(Teacher.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("TeacherReadService --> read failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return teachers;
	}

	@Override
	public List<Teacher> readAll() throws WSException {
		LOG.info("TeacherReadService --> readAll");

		List<Teacher> teachers = new ArrayList<>();

		try {
			dao.open();

			teachers = dao.getEntityDAO(Teacher.class).selectAll();
		} catch (DAOException e) {
			LOG.error("TeacherReadService --> readAll failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return teachers;
	}

	@Override
	public List<Teacher> deepRead(Map<String, String[]> parameters) throws WSException {
		throw new WSException("TeacherReadService --> deepRead unavailable");
	}

	@Override
	public List<Teacher> deepReadAll() throws WSException {
		throw new WSException("TeacherReadService --> deepReadAll unavailable");
	}
}

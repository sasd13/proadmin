package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;

public class TeacherService extends Service<Teacher> {

	private static final Logger LOGGER = Logger.getLogger(TeacherService.class);

	public TeacherService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Teacher teacher) {
		try {
			getSession(Teacher.class).insert(teacher);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Teacher> updateWrapper) {
		try {
			getSession(Teacher.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Teacher teacher) {
		try {
			getSession(Teacher.class).delete(teacher);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) {
		List<Teacher> teachers = new ArrayList<>();

		try {
			teachers = getSession(Teacher.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teachers;
	}

	@Override
	public List<Teacher> readAll() {
		List<Teacher> teachers = new ArrayList<>();

		try {
			teachers = getSession(Teacher.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teachers;
	}

	@Override
	public List<Teacher> deepRead(Map<String, String[]> parameters) {
		return read(parameters);
	}

	@Override
	public List<Teacher> deepReadAll() {
		return readAll();
	}
}

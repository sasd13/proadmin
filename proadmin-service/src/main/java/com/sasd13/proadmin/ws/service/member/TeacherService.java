package com.sasd13.proadmin.ws.service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.service.AbstractService;
import com.sasd13.proadmin.util.wrapper.update.member.ITeacherUpdateWrapper;

@Service
public class TeacherService extends AbstractService<Teacher> {

	private static final Logger LOGGER = Logger.getLogger(TeacherService.class);

	public TeacherService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Teacher teacher) {
		LOGGER.info("create : number=" + teacher.getNumber());

		try {
			currentConnection().getSession(Teacher.class).insert(teacher);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Teacher> updateWrapper) {
		LOGGER.info("update : number=" + ((ITeacherUpdateWrapper) updateWrapper).getNumber());

		try {
			currentConnection().getSession(Teacher.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Teacher teacher) {
		LOGGER.info("delete : number=" + teacher.getNumber());

		try {
			currentConnection().getSession(Teacher.class).delete(teacher);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Teacher> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Teacher> teachers = new ArrayList<>();

		try {
			teachers = currentConnection().getSession(Teacher.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teachers;
	}

	@Override
	public List<Teacher> readAll() {
		LOGGER.info("readAll");

		List<Teacher> teachers = new ArrayList<>();

		try {
			teachers = currentConnection().getSession(Teacher.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teachers;
	}

	@Override
	public List<Teacher> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Teacher> deepReadAll() {
		LOGGER.info("deepReadAll unavailable");
		throw new ServiceException("Service unavailable");
	}
}

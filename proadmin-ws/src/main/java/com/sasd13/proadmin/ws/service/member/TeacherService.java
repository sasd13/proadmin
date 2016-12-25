package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.util.wrapper.update.member.ITeacherUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

@Service
public class TeacherService extends AbstractService<Teacher> {

	private static final Logger LOGGER = Logger.getLogger(TeacherService.class);

	public TeacherService() {
		super();
	}

	@Override
	public void create(List<Teacher> teachers) {
		try {
			dao.open();

			ISession<Teacher> session = dao.getSession(Teacher.class);

			for (Teacher teacher : teachers) {
				LOGGER.info("create : number=" + teacher.getNumber());
				session.insert(teacher);
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
	public void update(List<IUpdateWrapper<Teacher>> updateWrappers) {
		try {
			dao.open();

			ISession<Teacher> session = dao.getSession(Teacher.class);
			ITeacherUpdateWrapper teacherUpdateWrapper;

			for (IUpdateWrapper<Teacher> updateWrapper : updateWrappers) {
				teacherUpdateWrapper = (ITeacherUpdateWrapper) updateWrapper;

				LOGGER.info("update : number=" + teacherUpdateWrapper.getNumber());
				session.update(teacherUpdateWrapper);
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
	public void delete(List<Teacher> teachers) {
		try {
			dao.open();

			ISession<Teacher> session = dao.getSession(Teacher.class);

			for (Teacher teacher : teachers) {
				LOGGER.info("delete : number=" + teacher.getNumber());
				session.delete(teacher);
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
	public List<Teacher> read(Map<String, String[]> parameters) {
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
	public List<Teacher> readAll() {
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

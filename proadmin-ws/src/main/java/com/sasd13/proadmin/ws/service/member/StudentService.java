package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class StudentService extends AbstractService<Student> {

	private static final Logger LOGGER = Logger.getLogger(StudentService.class);

	public StudentService() {
		super();
	}

	@Override
	public void create(List<Student> students) {
		try {
			dao.open();

			ISession<Student> session = dao.getSession(Student.class);

			for (Student student : students) {
				LOGGER.info("create : number=" + student.getNumber());
				session.insert(student);
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
	public void update(List<IUpdateWrapper<Student>> updateWrappers) {
		try {
			dao.open();

			ISession<Student> session = dao.getSession(Student.class);
			IStudentUpdateWrapper studentUpdateWrapper;

			for (IUpdateWrapper<Student> updateWrapper : updateWrappers) {
				studentUpdateWrapper = (IStudentUpdateWrapper) updateWrapper;

				LOGGER.info("update : number=" + studentUpdateWrapper.getNumber());
				session.update(studentUpdateWrapper);
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
	public void delete(List<Student> students) {
		try {
			dao.open();

			ISession<Student> session = dao.getSession(Student.class);

			for (Student student : students) {
				LOGGER.info("delete : number=" + student.getNumber());
				session.delete(student);
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
	public List<Student> read(Map<String, String[]> parameters) {
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
	public List<Student> readAll() {
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
	public List<Student> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Student> deepReadAll() {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}
}

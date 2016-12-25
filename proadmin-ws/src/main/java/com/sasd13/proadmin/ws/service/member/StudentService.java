package com.sasd13.proadmin.ws.service.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class StudentService extends AbstractService<Student> {

	private static final Logger LOGGER = Logger.getLogger(StudentService.class);

	public StudentService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Student student) {
		LOGGER.info("create : number=" + student.getNumber());

		try {
			currentConnection().getSession(Student.class).insert(student);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Student> updateWrapper) {
		LOGGER.info("update : number=" + ((IStudentUpdateWrapper) updateWrapper).getNumber());

		try {
			currentConnection().getSession(Student.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Student student) {
		LOGGER.info("delete : number=" + student.getNumber());

		try {
			currentConnection().getSession(Student.class).delete(student);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Student> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Student> students = new ArrayList<>();

		try {
			students = currentConnection().getSession(Student.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return students;
	}

	@Override
	public List<Student> readAll() {
		LOGGER.info("readAll");

		List<Student> students = new ArrayList<>();

		try {
			students = currentConnection().getSession(Student.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
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

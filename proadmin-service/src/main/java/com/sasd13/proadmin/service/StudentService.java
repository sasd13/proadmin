package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;

public class StudentService extends Service<Student> {

	private static final Logger LOGGER = Logger.getLogger(StudentService.class);

	public StudentService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Student student) {
		try {
			getSession(Student.class).insert(student);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Student> updateWrapper) {
		try {
			getSession(Student.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Student student) {
		try {
			getSession(Student.class).delete(student);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Student> read(Map<String, String[]> parameters) {
		List<Student> students = new ArrayList<>();

		try {
			students = getSession(Student.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return students;
	}

	@Override
	public List<Student> readAll() {
		List<Student> students = new ArrayList<>();

		try {
			students = getSession(Student.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return students;
	}

	@Override
	public List<Student> deepRead(Map<String, String[]> parameters) {
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Student> deepReadAll() {
		throw new ServiceException("Service unavailable");
	}
}

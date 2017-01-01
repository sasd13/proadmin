package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.IStudentDAO;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;
import com.sasd13.proadmin.ws2.service.IService;

public class StudentService implements IService<Student> {

	private static final Logger LOGGER = Logger.getLogger(StudentService.class);

	@Autowired
	private IStudentDAO dao;

	@Override
	public void create(Student student) {
		LOGGER.info("create : number=" + student.getNumber());

		try {
			dao.insert(student);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Student> updateWrapper) {
		LOGGER.info("update : number=" + ((IStudentUpdateWrapper) updateWrapper).getNumber());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Student student) {
		LOGGER.info("delete : number=" + student.getNumber());

		try {
			dao.delete(student);
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
			students = dao.select(parameters);
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
			students = dao.selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return students;
	}
}

package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class StudentManageService implements IManageService<Student> {

	private static final Logger LOG = Logger.getLogger(StudentManageService.class);

	private DAO dao;

	public StudentManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Student student) throws ServiceException {
		LOG.info("create : number=" + student.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Student.class).insert(student);
		} catch (DAOException e) {
			LOG.error("create failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Student student) throws ServiceException {
		LOG.info("update : number=" + student.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Student.class).update(student);
		} catch (DAOException e) {
			LOG.error("update failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(Student student) throws ServiceException {
		LOG.info("delete : number=" + student.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Student.class).delete(student);
		} catch (DAOException e) {
			LOG.error("delete failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}

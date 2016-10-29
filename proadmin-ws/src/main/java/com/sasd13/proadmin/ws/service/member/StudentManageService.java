package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class StudentManageService implements IManageService<Student> {

	private static final Logger LOG = Logger.getLogger(StudentManageService.class);

	private DAO dao;

	public StudentManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Student student) throws WSException {
		LOG.info("StudentManageService --> create : number=" + student.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Student.class).insert(student);
		} catch (DAOException e) {
			LOG.error("StudentManageService --> create failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Student student) throws WSException {
		LOG.info("StudentManageService --> update : number=" + student.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Student.class).update(student);
		} catch (DAOException e) {
			LOG.error("StudentManageService --> update failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(Student student) throws WSException {
		LOG.info("StudentManageService --> delete : number=" + student.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Student.class).update(student);
		} catch (DAOException e) {
			LOG.error("StudentManageService --> delete failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}

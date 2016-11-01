package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IEntityDAO;
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
	public void create(Student[] students) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Student> studentDAO = dao.getEntityDAO(Student.class);

			for (Student student : students) {
				LOG.info("create : number=" + student.getNumber());

				studentDAO.insert(student);
			}
		} catch (DAOException e) {
			LOG.error(e);
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
	public void update(Student[] students) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Student> studentDAO = dao.getEntityDAO(Student.class);

			for (Student student : students) {
				LOG.info("update : number=" + student.getNumber());
				studentDAO.update(student);
			}
		} catch (DAOException e) {
			LOG.error(e);
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
	public void delete(Student[] students) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Student> studentDAO = dao.getEntityDAO(Student.class);

			for (Student student : students) {
				LOG.info("delete : number=" + student.getNumber());
				studentDAO.delete(student);
			}
		} catch (DAOException e) {
			LOG.error(e);
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

package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.member.IStudentUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class StudentManageService implements IManageService<Student> {

	private static final Logger LOGGER = Logger.getLogger(StudentManageService.class);

	private DAO dao;

	public StudentManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Student> students) throws ServiceException {
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
	public void update(List<IUpdateWrapper<Student>> updateWrappers) throws ServiceException {
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
	public void delete(List<Student> students) throws ServiceException {
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
}

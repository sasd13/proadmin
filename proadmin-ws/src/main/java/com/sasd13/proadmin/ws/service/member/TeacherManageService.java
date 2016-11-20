package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.member.ITeacherUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class TeacherManageService implements IManageService<Teacher> {

	private static final Logger LOGGER = Logger.getLogger(TeacherManageService.class);

	private DAO dao;

	public TeacherManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Teacher> teachers) throws ServiceException {
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
	public void update(List<IUpdateWrapper<Teacher>> updateWrappers) throws ServiceException {
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
	public void delete(List<Teacher> teachers) throws ServiceException {
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
}

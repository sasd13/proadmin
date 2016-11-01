package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class TeacherManageService implements IManageService<Teacher> {

	private static final Logger LOG = Logger.getLogger(TeacherManageService.class);

	private DAO dao;

	public TeacherManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Teacher[] teachers) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Teacher> teacherDAO = dao.getEntityDAO(Teacher.class);

			for (Teacher teacher : teachers) {
				LOG.info("create : number=" + teacher.getNumber());
				teacherDAO.insert(teacher);
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
	public void update(Teacher[] teachers) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Teacher> teacherDAO = dao.getEntityDAO(Teacher.class);

			for (Teacher teacher : teachers) {
				LOG.info("update : number=" + teacher.getNumber());
				teacherDAO.update(teacher);
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
	public void delete(Teacher[] teachers) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Teacher> teacherDAO = dao.getEntityDAO(Teacher.class);

			for (Teacher teacher : teachers) {
				LOG.info("delete : number=" + teacher.getNumber());
				teacherDAO.delete(teacher);
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

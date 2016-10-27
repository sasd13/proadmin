package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class TeacherManageService implements IManageService<Teacher> {

	private static final Logger LOG = Logger.getLogger(TeacherManageService.class);

	private DAO dao;

	public TeacherManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Teacher teacher) throws WSException {
		try {
			LOG.error("TeacherManageService --> create : " + teacher.getNumber());
			dao.open();
			dao.getEntityDAO(Teacher.class).insert(teacher);
		} catch (DAOException e) {
			LOG.error(e);
			throw new WSException("TeacherManageService --> create failed");
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Teacher teacher) throws WSException {
		try {
			LOG.error("TeacherManageService --> update : " + teacher.getNumber());
			dao.open();
			dao.getEntityDAO(Teacher.class).update(teacher);
		} catch (DAOException e) {
			LOG.error(e);
			throw new WSException("TeacherManageService --> update failed");
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(Teacher teacher) throws WSException {
		try {
			LOG.error("TeacherManageService --> delete : " + teacher.getNumber());
			dao.open();
			dao.getEntityDAO(Teacher.class).update(teacher);
		} catch (DAOException e) {
			LOG.error(e);
			throw new WSException("TeacherManageService --> delete failed");
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}

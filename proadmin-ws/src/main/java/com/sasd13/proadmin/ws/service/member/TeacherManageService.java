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
		LOG.info("TeacherManageService --> create : number=" + teacher.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Teacher.class).insert(teacher);
		} catch (DAOException e) {
			LOG.error("TeacherManageService --> create failed", e);
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
	public void update(Teacher teacher) throws WSException {
		LOG.info("TeacherManageService --> update : number=" + teacher.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Teacher.class).update(teacher);
		} catch (DAOException e) {
			LOG.error("TeacherManageService --> update failed", e);
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
	public void delete(Teacher teacher) throws WSException {
		LOG.info("TeacherManageService --> delete : number=" + teacher.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Teacher.class).update(teacher);
		} catch (DAOException e) {
			LOG.error("TeacherManageService --> delete failed", e);
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

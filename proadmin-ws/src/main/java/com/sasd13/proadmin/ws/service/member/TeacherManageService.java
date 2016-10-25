package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.service.DAOManager;
import com.sasd13.proadmin.ws.service.IManageService;

public class TeacherManageService implements IManageService<Teacher> {

	private static final Logger LOG = Logger.getLogger(TeacherManageService.class);

	private DAO dao;

	public TeacherManageService() {
		dao = DAOManager.create();
	}

	@Override
	public long create(Teacher teacher) {
		long id = 0;

		try {
			LOG.error("Insert teacher : " + teacher.getNumber());
			dao.open();

			id = dao.getEntityDAO(Teacher.class).insert(teacher);
		} catch (DAOException e) {
			LOG.error("Insert failed", e);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}

		return id;
	}

	@Override
	public void update(Teacher teacher) {
		try {
			LOG.error("Update teacher : " + teacher.getNumber());
			dao.open();
			dao.getEntityDAO(Teacher.class).update(teacher);
		} catch (DAOException e) {
			LOG.error("Update failed", e);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
	}

	@Override
	public void delete(Teacher teacher) {
		try {
			LOG.error("Delete teacher : " + teacher.getNumber());
			dao.open();
			dao.getEntityDAO(Teacher.class).update(teacher);
		} catch (DAOException e) {
			LOG.error("Delete failed", e);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}
	}
}

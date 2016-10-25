package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.service.DAOManager;
import com.sasd13.proadmin.ws.service.IReadService;

public class TeacherReadService implements IReadService<Teacher> {

	private static final Logger LOG = Logger.getLogger(TeacherReadService.class);

	private DAO dao;

	public TeacherReadService() {
		dao = DAOManager.create();
	}

	@Override
	public Teacher read(Teacher teacher) {
		Teacher result = null;

		try {
			dao.open();

			result = dao.getEntityDAO(Teacher.class).select(teacher.getId());
		} catch (DAOException e) {
			LOG.error("Read failed", e);
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.error(e);
			}
		}

		return result;
	}

	@Override
	public Teacher deepRead(Teacher teacher) {
		LOG.error("Teacher deep read failed");
		
		return null;
	}
}

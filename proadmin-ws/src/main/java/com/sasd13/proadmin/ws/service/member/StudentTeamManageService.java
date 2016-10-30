package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class StudentTeamManageService implements IManageService<StudentTeam> {

	private static final Logger LOG = Logger.getLogger(StudentTeamManageService.class);

	private DAO dao;

	public StudentTeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(StudentTeam studentTeam) throws WSException {
		LOG.info("create : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(StudentTeam.class).insert(studentTeam);
		} catch (DAOException e) {
			LOG.error("create failed");
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
	public void update(StudentTeam studentTeam) throws WSException {
		LOG.info("update unavailable");
		throw new WSException("Service unavailable");
	}

	@Override
	public void delete(StudentTeam studentTeam) throws WSException {
		LOG.info("delete : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(StudentTeam.class).delete(studentTeam);
		} catch (DAOException e) {
			LOG.error("delete failed");
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
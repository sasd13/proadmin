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
		LOG.info("StudentTeamManageService --> create : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(StudentTeam.class).insert(studentTeam);
		} catch (DAOException e) {
			LOG.error("StudentTeamManageService --> create failed", e);
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
		LOG.info("StudentTeamManageService --> update : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(StudentTeam.class).update(studentTeam);
		} catch (DAOException e) {
			LOG.error("StudentTeamManageService --> update failed", e);
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
	public void delete(StudentTeam studentTeam) throws WSException {
		LOG.info("StudentTeamManageService --> delete : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(StudentTeam.class).update(studentTeam);
		} catch (DAOException e) {
			LOG.error("StudentTeamManageService --> delete failed", e);
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

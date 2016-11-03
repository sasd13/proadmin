package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class StudentTeamManageService implements IManageService<StudentTeam> {

	private static final Logger LOG = Logger.getLogger(StudentTeamManageService.class);

	private DAO dao;

	public StudentTeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<StudentTeam> studentTeams) throws ServiceException {
		try {
			dao.open();

			IManager<StudentTeam> studentTeamDAO = dao.getSession(StudentTeam.class);

			for (StudentTeam studentTeam : studentTeams) {
				LOG.info("create : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());
				studentTeamDAO.insert(studentTeam);
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
	public void update(List<StudentTeam> studentTeams) throws ServiceException {
		LOG.info("update unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void delete(List<StudentTeam> studentTeams) throws ServiceException {
		try {
			dao.open();

			IManager<StudentTeam> studentTeamDAO = dao.getSession(StudentTeam.class);

			for (StudentTeam studentTeam : studentTeams) {
				LOG.info("delete : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());
				studentTeamDAO.delete(studentTeam);
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

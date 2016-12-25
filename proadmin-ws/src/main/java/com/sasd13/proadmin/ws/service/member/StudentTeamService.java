package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.ws.service.AbstractService;

public class StudentTeamService extends AbstractService<StudentTeam> {

	private static final Logger LOGGER = Logger.getLogger(StudentTeamService.class);

	public StudentTeamService() {
		super();
	}

	@Override
	public void create(List<StudentTeam> studentTeams) {
		try {
			dao.open();

			ISession<StudentTeam> session = dao.getSession(StudentTeam.class);

			for (StudentTeam studentTeam : studentTeams) {
				LOGGER.info("create : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());
				session.insert(studentTeam);
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
	public void update(List<IUpdateWrapper<StudentTeam>> updateWrappers) {
		LOGGER.info("update unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void delete(List<StudentTeam> studentTeams) {
		try {
			dao.open();

			ISession<StudentTeam> session = dao.getSession(StudentTeam.class);

			for (StudentTeam studentTeam : studentTeams) {
				LOGGER.info("delete : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());
				session.delete(studentTeam);
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
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			dao.open();

			studentTeams = dao.getSession(StudentTeam.class).select(parameters);
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

		return studentTeams;
	}

	@Override
	public List<StudentTeam> readAll() {
		LOGGER.info("readAll");

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			dao.open();

			studentTeams = dao.getSession(StudentTeam.class).selectAll();
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

		return studentTeams;
	}

	@Override
	public List<StudentTeam> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			dao.open();

			studentTeams = dao.getDeepReader(StudentTeam.class).select(parameters);
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

		return studentTeams;
	}

	@Override
	public List<StudentTeam> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			dao.open();

			studentTeams = dao.getDeepReader(StudentTeam.class).selectAll();
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

		return studentTeams;
	}
}

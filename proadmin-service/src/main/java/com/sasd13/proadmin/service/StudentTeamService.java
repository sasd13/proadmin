package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.dao.DAO;

public class StudentTeamService extends Service<StudentTeam> {

	private static final Logger LOGGER = Logger.getLogger(StudentTeamService.class);

	public StudentTeamService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(StudentTeam studentTeam) {
		LOGGER.info("create : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamNumber=" + studentTeam.getTeam().getNumber());

		try {
			currentConnection().getSession(StudentTeam.class).insert(studentTeam);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<StudentTeam> updateWrapper) {
		LOGGER.info("update unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public void delete(StudentTeam studentTeam) {
		LOGGER.info("delete : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamNumber=" + studentTeam.getTeam().getNumber());

		try {
			currentConnection().getSession(StudentTeam.class).delete(studentTeam);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			studentTeams = currentConnection().getSession(StudentTeam.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return studentTeams;
	}

	@Override
	public List<StudentTeam> readAll() {
		LOGGER.info("readAll");

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			studentTeams = currentConnection().getSession(StudentTeam.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return studentTeams;
	}

	@Override
	public List<StudentTeam> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			studentTeams = currentConnection().getDeepReader(StudentTeam.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return studentTeams;
	}

	@Override
	public List<StudentTeam> deepReadAll() {
		LOGGER.info("deepReadAll");

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			studentTeams = currentConnection().getDeepReader(StudentTeam.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return studentTeams;
	}
}

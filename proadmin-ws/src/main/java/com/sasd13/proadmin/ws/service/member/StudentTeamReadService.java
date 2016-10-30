package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class StudentTeamReadService implements IReadService<StudentTeam> {

	private static final Logger LOG = Logger.getLogger(StudentTeamReadService.class);

	private DAO dao;

	public StudentTeamReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) throws ServiceException {
		LOG.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			dao.open();

			studentTeams = dao.getEntityDAO(StudentTeam.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("read failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return studentTeams;
	}

	@Override
	public List<StudentTeam> readAll() throws ServiceException {
		LOG.info("readAll");

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			dao.open();

			studentTeams = dao.getEntityDAO(StudentTeam.class).selectAll();
		} catch (DAOException e) {
			LOG.error("readAll failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return studentTeams;
	}

	@Override
	public List<StudentTeam> deepRead(Map<String, String[]> parameters) throws ServiceException {
		LOG.info("deepRead : parameters=" + URLQueryUtils.toString(parameters));

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			dao.open();

			studentTeams = dao.getDeepReader(StudentTeam.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("deepRead failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return studentTeams;
	}

	@Override
	public List<StudentTeam> deepReadAll() throws ServiceException {
		LOG.info("deepReadAll");

		List<StudentTeam> studentTeams = new ArrayList<>();

		try {
			dao.open();

			studentTeams = dao.getDeepReader(StudentTeam.class).selectAll();
		} catch (DAOException e) {
			LOG.error("deepReadAll failed");
			throw new ServiceException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return studentTeams;
	}
}

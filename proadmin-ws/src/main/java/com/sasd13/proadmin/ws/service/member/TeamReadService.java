package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class TeamReadService implements IReadService<Team> {

	private static final Logger LOGGER = Logger.getLogger(TeamReadService.class);

	private DAO dao;

	public TeamReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Team> read(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Team> teams = new ArrayList<>();

		try {
			dao.open();

			teams = dao.getSession(Team.class).select(parameters);
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

		return teams;
	}

	@Override
	public List<Team> readAll() throws ServiceException {
		LOGGER.info("readAll");

		List<Team> teams = new ArrayList<>();

		try {
			dao.open();

			teams = dao.getSession(Team.class).selectAll();
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

		return teams;
	}

	@Override
	public List<Team> deepRead(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Team> deepReadAll() throws ServiceException {
		LOGGER.info("deepReadAll unavailable");
		throw new ServiceException("Service unavailable");
	}
}

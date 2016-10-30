package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class TeamReadService implements IReadService<Team> {

	private static final Logger LOG = Logger.getLogger(TeamReadService.class);

	private DAO dao;

	public TeamReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Team> read(Map<String, String[]> parameters) throws WSException {
		LOG.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Team> teams = new ArrayList<>();

		try {
			dao.open();

			teams = dao.getEntityDAO(Team.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("read failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return teams;
	}

	@Override
	public List<Team> readAll() throws WSException {
		LOG.info("readAll");

		List<Team> teams = new ArrayList<>();

		try {
			dao.open();

			teams = dao.getEntityDAO(Team.class).selectAll();
		} catch (DAOException e) {
			LOG.error("readAll failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return teams;
	}

	@Override
	public List<Team> deepRead(Map<String, String[]> parameters) throws WSException {
		LOG.info("deepRead unavailable");
		throw new WSException("Service unavailable");
	}

	@Override
	public List<Team> deepReadAll() throws WSException {
		LOG.info("deepReadAll unavailable");
		throw new WSException("Service unavailable");
	}
}

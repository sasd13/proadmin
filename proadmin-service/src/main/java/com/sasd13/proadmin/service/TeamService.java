package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.member.ITeamUpdateWrapper;

public class TeamService extends Service<Team> {

	private static final Logger LOGGER = Logger.getLogger(TeamService.class);

	public TeamService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Team team) {
		LOGGER.info("create : number=" + team.getNumber());

		try {
			currentConnection().getSession(Team.class).insert(team);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Team> updateWrapper) {
		LOGGER.info("update : number=" + ((ITeamUpdateWrapper) updateWrapper).getNumber());

		try {
			currentConnection().getSession(Team.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Team team) {
		LOGGER.info("delete : number=" + team.getNumber());

		try {
			currentConnection().getSession(Team.class).delete(team);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Team> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Team> teams = new ArrayList<>();

		try {
			teams = currentConnection().getSession(Team.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teams;
	}

	@Override
	public List<Team> readAll() {
		LOGGER.info("readAll");

		List<Team> teams = new ArrayList<>();

		try {
			teams = currentConnection().getSession(Team.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teams;
	}

	@Override
	public List<Team> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Team> deepReadAll() {
		LOGGER.info("deepReadAll unavailable");
		throw new ServiceException("Service unavailable");
	}
}

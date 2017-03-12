package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;

public class TeamService extends Service<Team> {

	private static final Logger LOGGER = Logger.getLogger(TeamService.class);

	public TeamService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Team team) {
		try {
			getSession(Team.class).insert(team);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Team> updateWrapper) {
		try {
			getSession(Team.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Team team) {
		try {
			getSession(Team.class).delete(team);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Team> read(Map<String, String[]> parameters) {
		List<Team> teams = new ArrayList<>();

		try {
			teams = getSession(Team.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teams;
	}

	@Override
	public List<Team> readAll() {
		List<Team> teams = new ArrayList<>();

		try {
			teams = getSession(Team.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teams;
	}

	@Override
	public List<Team> deepRead(Map<String, String[]> parameters) {
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Team> deepReadAll() {
		throw new ServiceException("Service unavailable");
	}
}

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
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.wrapper.update.member.ITeamUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class TeamService extends AbstractService<Team> {

	private static final Logger LOGGER = Logger.getLogger(TeamService.class);

	public TeamService() {
		super();
	}

	@Override
	public void create(List<Team> teams) {
		try {
			dao.open();

			ISession<Team> session = dao.getSession(Team.class);

			for (Team team : teams) {
				LOGGER.info("create : code=" + team.getNumber());
				session.insert(team);
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
	public void update(List<IUpdateWrapper<Team>> updateWrappers) {
		try {
			dao.open();

			ISession<Team> session = dao.getSession(Team.class);
			ITeamUpdateWrapper teamUpdateWrapper;

			for (IUpdateWrapper<Team> updateWrapper : updateWrappers) {
				teamUpdateWrapper = (ITeamUpdateWrapper) updateWrapper;

				LOGGER.info("update : code=" + teamUpdateWrapper.getNumber());
				session.update(teamUpdateWrapper);
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
	public void delete(List<Team> teams) {
		try {
			dao.open();

			ISession<Team> session = dao.getSession(Team.class);

			for (Team team : teams) {
				LOGGER.info("delete : code=" + team.getNumber());
				session.delete(team);
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
	public List<Team> read(Map<String, String[]> parameters) {
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
	public List<Team> readAll() {
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

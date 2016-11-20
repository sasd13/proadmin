package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.member.ITeamUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class TeamManageService implements IManageService<Team> {

	private static final Logger LOGGER = Logger.getLogger(TeamManageService.class);

	private DAO dao;

	public TeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Team> teams) throws ServiceException {
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
	public void update(List<IUpdateWrapper<Team>> updateWrappers) throws ServiceException {
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
	public void delete(List<Team> teams) throws ServiceException {
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
}

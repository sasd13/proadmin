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

	private static final Logger LOG = Logger.getLogger(TeamManageService.class);

	private DAO dao;

	public TeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Team> teams) throws ServiceException {
		try {
			dao.open();

			ISession<Team> teamDAO = dao.getSession(Team.class);

			for (Team team : teams) {
				LOG.info("create : code=" + team.getNumber());
				teamDAO.insert(team);
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
	public void update(List<IUpdateWrapper<Team>> updateWrappers) throws ServiceException {
		try {
			dao.open();

			ISession<Team> teamDAO = dao.getSession(Team.class);
			ITeamUpdateWrapper teamUpdateWrapper;

			for (IUpdateWrapper<Team> updateWrapper : updateWrappers) {
				teamUpdateWrapper = (ITeamUpdateWrapper) updateWrapper;

				LOG.info("update : code=" + teamUpdateWrapper.getNumber());
				teamDAO.update(teamUpdateWrapper);
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
	public void delete(List<Team> teams) throws ServiceException {
		try {
			dao.open();

			ISession<Team> teamDAO = dao.getSession(Team.class);

			for (Team team : teams) {
				LOG.info("delete : code=" + team.getNumber());
				teamDAO.delete(team);
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

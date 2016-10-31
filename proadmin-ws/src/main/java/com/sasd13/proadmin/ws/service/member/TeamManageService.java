package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class TeamManageService implements IManageService<Team> {

	private static final Logger LOG = Logger.getLogger(TeamManageService.class);

	private DAO dao;

	public TeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Team[] teams) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Team> teamDAO = dao.getEntityDAO(Team.class);

			for (Team team : teams) {
				LOG.info("create : code=" + team.getNumber());

				long id = teamDAO.insert(team);

				LOG.info("created with id=" + id);
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
	public void update(Team[] teams) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Team> teamDAO = dao.getEntityDAO(Team.class);

			for (Team team : teams) {
				LOG.info("update : code=" + team.getNumber());
				teamDAO.update(team);
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
	public void delete(Team[] teams) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Team> teamDAO = dao.getEntityDAO(Team.class);

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

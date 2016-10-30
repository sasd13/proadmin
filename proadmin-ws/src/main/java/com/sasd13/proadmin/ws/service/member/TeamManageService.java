package com.sasd13.proadmin.ws.service.member;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class TeamManageService implements IManageService<Team> {

	private static final Logger LOG = Logger.getLogger(TeamManageService.class);

	private DAO dao;

	public TeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Team team) throws WSException {
		LOG.info("create : code=" + team.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Team.class).insert(team);
		} catch (DAOException e) {
			LOG.error("create failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Team team) throws WSException {
		LOG.info("update : code=" + team.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Team.class).update(team);
		} catch (DAOException e) {
			LOG.error("update failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(Team team) throws WSException {
		LOG.info("delete : code=" + team.getNumber());

		try {
			dao.open();
			dao.getEntityDAO(Team.class).update(team);
		} catch (DAOException e) {
			LOG.error("delete failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}

package com.sasd13.proadmin.ws.service.running;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class RunningTeamManageService implements IManageService<RunningTeam> {

	private static final Logger LOG = Logger.getLogger(RunningTeamManageService.class);

	private DAO dao;

	public RunningTeamManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(RunningTeam runningTeam) throws WSException {
		LOG.info("RunningTeamManageService --> create : teamCode=" + runningTeam.getTeam().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(RunningTeam.class).insert(runningTeam);
		} catch (DAOException e) {
			LOG.error("RunningTeamManageService --> create failed", e);
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
	public void update(RunningTeam runningTeam) throws WSException {
		LOG.info("RunningTeamManageService --> update : teamCode=" + runningTeam.getTeam().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(RunningTeam.class).update(runningTeam);
		} catch (DAOException e) {
			LOG.error("RunningTeamManageService --> update failed", e);
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
	public void delete(RunningTeam runningTeam) throws WSException {
		LOG.info("RunningTeamManageService --> delete : teamCode=" + runningTeam.getTeam().getNumber());

		try {
			dao.open();
			dao.getEntityDAO(RunningTeam.class).update(runningTeam);
		} catch (DAOException e) {
			LOG.error("RunningTeamManageService --> delete failed", e);
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

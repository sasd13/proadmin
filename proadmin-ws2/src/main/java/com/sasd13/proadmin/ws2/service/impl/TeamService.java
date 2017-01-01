package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.ITeamDAO;
import com.sasd13.proadmin.util.wrapper.update.member.ITeamUpdateWrapper;
import com.sasd13.proadmin.ws2.service.IService;

public class TeamService implements IService<Team> {

	private static final Logger LOGGER = Logger.getLogger(TeamService.class);

	@Autowired
	private ITeamDAO dao;

	@Override
	public void create(Team team) {
		LOGGER.info("create : number=" + team.getNumber());

		try {
			dao.insert(team);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Team> updateWrapper) {
		LOGGER.info("update : number=" + ((ITeamUpdateWrapper) updateWrapper).getNumber());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Team team) {
		LOGGER.info("delete : number=" + team.getNumber());

		try {
			dao.delete(team);
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
			teams = dao.select(parameters);
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
			teams = dao.selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return teams;
	}
}

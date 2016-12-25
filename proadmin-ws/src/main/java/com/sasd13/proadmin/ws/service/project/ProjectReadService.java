package com.sasd13.proadmin.ws.service.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.IDeepReadService;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class ProjectReadService implements IReadService<Project>, IDeepReadService<Project> {

	private static final Logger LOGGER = Logger.getLogger(ProjectReadService.class);

	private DAO dao;

	public ProjectReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Project> projects = new ArrayList<>();

		try {
			dao.open();

			projects = dao.getSession(Project.class).select(parameters);
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

		return projects;
	}

	@Override
	public List<Project> readAll() throws ServiceException {
		LOGGER.info("readAll");

		List<Project> projects = new ArrayList<>();

		try {
			dao.open();

			projects = dao.getSession(Project.class).selectAll();
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

		return projects;
	}

	@Override
	public List<Project> deepRead(Map<String, String[]> parameters) throws ServiceException {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Project> deepReadAll() throws ServiceException {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}
}

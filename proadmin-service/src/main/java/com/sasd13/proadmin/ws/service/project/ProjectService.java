package com.sasd13.proadmin.ws.service.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.service.Service;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;

public class ProjectService extends Service<Project> {

	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	public ProjectService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Project project) {
		LOGGER.info("create : code=" + project.getCode());

		try {
			currentConnection().getSession(Project.class).insert(project);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Project> updateWrapper) {
		LOGGER.info("update : code=" + ((IProjectUpdateWrapper) updateWrapper).getCode());

		try {
			currentConnection().getSession(Project.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Project project) {
		LOGGER.info("delete : code=" + project.getCode());

		try {
			currentConnection().getSession(Project.class).delete(project);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) {
		LOGGER.info("read : parameters=" + URLQueryUtils.toString(parameters));

		List<Project> projects = new ArrayList<>();

		try {
			projects = currentConnection().getSession(Project.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return projects;
	}

	@Override
	public List<Project> readAll() {
		LOGGER.info("readAll");

		List<Project> projects = new ArrayList<>();

		try {
			projects = currentConnection().getSession(Project.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return projects;
	}

	@Override
	public List<Project> deepRead(Map<String, String[]> parameters) {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Project> deepReadAll() {
		LOGGER.info("deepRead unavailable");
		throw new ServiceException("Service unavailable");
	}
}

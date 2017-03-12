package com.sasd13.proadmin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;

public class ProjectService extends Service<Project> {

	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	public ProjectService(DAO dao) {
		super(dao);
	}

	@Override
	public void create(Project project) {
		try {
			getSession(Project.class).insert(project);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Project> updateWrapper) {
		try {
			getSession(Project.class).update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Project project) {
		try {
			getSession(Project.class).delete(project);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) {
		List<Project> projects = new ArrayList<>();

		try {
			projects = getSession(Project.class).select(parameters);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return projects;
	}

	@Override
	public List<Project> readAll() {
		List<Project> projects = new ArrayList<>();

		try {
			projects = getSession(Project.class).selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return projects;
	}

	@Override
	public List<Project> deepRead(Map<String, String[]> parameters) {
		throw new ServiceException("Service unavailable");
	}

	@Override
	public List<Project> deepReadAll() {
		throw new ServiceException("Service unavailable");
	}
}

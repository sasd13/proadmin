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
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IProjectDAO;
import com.sasd13.proadmin.ws2.service.IService;

public class ProjectService implements IService<Project> {

	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	@Autowired
	private IProjectDAO dao;

	@Override
	public void create(Project project) {
		LOGGER.info("create : code=" + project.getCode());

		try {
			dao.insert(project);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void update(IUpdateWrapper<Project> updateWrapper) {
		LOGGER.info("update : code=" + ((IProjectUpdateWrapper) updateWrapper).getCode());

		try {
			dao.update(updateWrapper);
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void delete(Project project) {
		LOGGER.info("delete : code=" + project.getCode());

		try {
			dao.delete(project);
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
			projects = dao.select(parameters);
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
			projects = dao.selectAll();
		} catch (DAOException e) {
			LOGGER.error(e);
			throw new ServiceException(e.getMessage());
		}

		return projects;
	}
}

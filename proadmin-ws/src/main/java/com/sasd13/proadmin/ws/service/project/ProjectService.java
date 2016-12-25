package com.sasd13.proadmin.ws.service.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;
import com.sasd13.proadmin.ws.service.AbstractService;

public class ProjectService extends AbstractService<Project> {

	private static final Logger LOGGER = Logger.getLogger(ProjectService.class);

	public ProjectService() {
		super();
	}

	@Override
	public void create(List<Project> projects) {
		try {
			dao.open();

			ISession<Project> session = dao.getSession(Project.class);

			for (Project project : projects) {
				LOGGER.info("create : code=" + project.getCode());
				session.insert(project);
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
	public void update(List<IUpdateWrapper<Project>> updateWrappers) {
		try {
			dao.open();

			ISession<Project> session = dao.getSession(Project.class);
			IProjectUpdateWrapper projectUpdateWrapper;

			for (IUpdateWrapper<Project> updateWrapper : updateWrappers) {
				projectUpdateWrapper = (IProjectUpdateWrapper) updateWrapper;

				LOGGER.info("update : code=" + projectUpdateWrapper.getCode());
				session.update(projectUpdateWrapper);
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
	public void delete(List<Project> projects) {
		try {
			dao.open();

			ISession<Project> session = dao.getSession(Project.class);

			for (Project project : projects) {
				LOGGER.info("delete : code=" + project.getCode());
				session.delete(project);
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
	public List<Project> read(Map<String, String[]> parameters) {
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
	public List<Project> readAll() {
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

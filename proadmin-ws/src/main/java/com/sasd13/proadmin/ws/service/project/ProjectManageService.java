package com.sasd13.proadmin.ws.service.project;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.ISession;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class ProjectManageService implements IManageService<Project> {

	private static final Logger LOGGER = Logger.getLogger(ProjectManageService.class);

	private DAO dao;

	public ProjectManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Project> projects) throws ServiceException {
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
	public void update(List<IUpdateWrapper<Project>> updateWrappers) throws ServiceException {
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
	public void delete(List<Project> projects) throws ServiceException {
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
}

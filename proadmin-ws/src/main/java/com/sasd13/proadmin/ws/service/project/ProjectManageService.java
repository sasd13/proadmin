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

	private static final Logger LOG = Logger.getLogger(ProjectManageService.class);

	private DAO dao;

	public ProjectManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(List<Project> projects) throws ServiceException {
		try {
			dao.open();

			ISession<Project> projectDAO = dao.getSession(Project.class);

			for (Project project : projects) {
				LOG.info("create : code=" + project.getCode());
				projectDAO.insert(project);
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
	public void update(List<IUpdateWrapper<Project>> updateWrappers) throws ServiceException {
		try {
			dao.open();

			ISession<Project> projectDAO = dao.getSession(Project.class);
			IProjectUpdateWrapper projectUpdateWrapper;

			for (IUpdateWrapper<Project> updateWrapper : updateWrappers) {
				projectUpdateWrapper = (IProjectUpdateWrapper) updateWrapper;

				LOG.info("update : code=" + projectUpdateWrapper.getCode());
				projectDAO.update(projectUpdateWrapper);
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
	public void delete(List<Project> projects) throws ServiceException {
		try {
			dao.open();

			ISession<Project> projectDAO = dao.getSession(Project.class);

			for (Project project : projects) {
				LOG.info("delete : code=" + project.getCode());
				projectDAO.delete(project);
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

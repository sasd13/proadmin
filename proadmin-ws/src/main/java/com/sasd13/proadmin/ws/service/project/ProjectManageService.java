package com.sasd13.proadmin.ws.service.project;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.javaex.service.ServiceException;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;

public class ProjectManageService implements IManageService<Project> {

	private static final Logger LOG = Logger.getLogger(ProjectManageService.class);

	private DAO dao;

	public ProjectManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Project[] projects) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Project> projectDAO = dao.getEntityDAO(Project.class);

			for (Project project : projects) {
				LOG.info("create : code=" + project.getCode());

				long id = projectDAO.insert(project);

				LOG.info("created with id=" + id);
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
	public void update(Project[] projects) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Project> projectDAO = dao.getEntityDAO(Project.class);

			for (Project project : projects) {
				LOG.info("update : code=" + project.getCode());
				projectDAO.update(project);
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
	public void delete(Project[] projects) throws ServiceException {
		try {
			dao.open();

			IEntityDAO<Project> projectDAO = dao.getEntityDAO(Project.class);

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

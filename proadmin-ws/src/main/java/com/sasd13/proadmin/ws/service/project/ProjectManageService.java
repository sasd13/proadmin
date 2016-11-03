package com.sasd13.proadmin.ws.service.project;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IManager;
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
	public void create(List<Project> projects) throws ServiceException {
		try {
			dao.open();

			IManager<Project> projectDAO = dao.getSession(Project.class);

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
	public void update(List<Project> projects) throws ServiceException {
		try {
			dao.open();

			IManager<Project> projectDAO = dao.getSession(Project.class);

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
	public void delete(List<Project> projects) throws ServiceException {
		try {
			dao.open();

			IManager<Project> projectDAO = dao.getSession(Project.class);

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

package com.sasd13.proadmin.ws.service.project;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.service.IManageService;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class ProjectManageService implements IManageService<Project> {

	private static final Logger LOG = Logger.getLogger(ProjectManageService.class);

	private DAO dao;

	public ProjectManageService() {
		dao = DAOManager.create();
	}

	@Override
	public void create(Project project) throws WSException {
		LOG.info("create : code=" + project.getCode());

		try {
			dao.open();
			dao.getEntityDAO(Project.class).insert(project);
		} catch (DAOException e) {
			LOG.error("create failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Project project) throws WSException {
		LOG.info("update : code=" + project.getCode());

		try {
			dao.open();
			dao.getEntityDAO(Project.class).update(project);
		} catch (DAOException e) {
			LOG.error("update failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void delete(Project project) throws WSException {
		LOG.info("delete : code=" + project.getCode());

		try {
			dao.open();
			dao.getEntityDAO(Project.class).delete(project);
		} catch (DAOException e) {
			LOG.error("delete failed");
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}
	}
}

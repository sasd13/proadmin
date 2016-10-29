package com.sasd13.proadmin.ws.service.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.javaex.service.IReadService;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.ws.dao.DAOManager;
import com.sasd13.proadmin.ws.service.WSException;

public class ProjectReadService implements IReadService<Project> {

	private static final Logger LOG = Logger.getLogger(ProjectReadService.class);

	private DAO dao;

	public ProjectReadService() {
		dao = DAOManager.create();
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) throws WSException {
		LOG.info("ProjectReadService --> read : parameters=" + URLQueryUtils.toString(parameters));

		List<Project> projects = new ArrayList<>();

		try {
			dao.open();

			projects = dao.getEntityDAO(Project.class).select(parameters);
		} catch (DAOException e) {
			LOG.error("ProjectReadService --> read failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return projects;
	}

	@Override
	public List<Project> readAll() throws WSException {
		LOG.info("ProjectReadService --> readAll");

		List<Project> projects = new ArrayList<>();

		try {
			dao.open();

			projects = dao.getEntityDAO(Project.class).selectAll();
		} catch (DAOException e) {
			LOG.error("ProjectReadService --> readAll failed", e);
			throw new WSException(e.getMessage());
		} finally {
			try {
				dao.close();
			} catch (IOException e) {
				LOG.warn(e);
			}
		}

		return projects;
	}

	@Override
	public List<Project> deepRead(Map<String, String[]> parameters) throws WSException {
		throw new WSException("ProjectReadService --> deepRead unavailable");
	}

	@Override
	public List<Project> deepReadAll() throws WSException {
		throw new WSException("ProjectReadService --> deepReadAll unavailable");
	}
}

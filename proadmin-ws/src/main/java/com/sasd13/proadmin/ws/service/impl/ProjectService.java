package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.IProjectDAO;
import com.sasd13.proadmin.ws.service.IProjectService;

public class ProjectService implements IProjectService {

	private IProjectDAO projectDAO;

	public ProjectService(DAO dao) {
		projectDAO = (IProjectDAO) dao.getSession(IProjectDAO.class);
	}

	@Override
	public long create(IProject iProject) {
		return projectDAO.create(iProject);
	}

	@Override
	public void update(ProjectUpdateWrapper updateWrapper) {
		projectDAO.update(updateWrapper);
	}

	@Override
	public void delete(IProject iProject) {
		projectDAO.delete(iProject);
	}

	@Override
	public List<IProject> read(Map<String, String[]> parameters) {
		return projectDAO.read(parameters);
	}

	@Override
	public List<IProject> readAll() {
		return projectDAO.readAll();
	}
}

package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.Project;
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
	public long create(Project project) {
		return projectDAO.create(project);
	}

	@Override
	public void update(ProjectUpdateWrapper updateWrapper) {
		projectDAO.update(updateWrapper);
	}

	@Override
	public void delete(Project project) {
		projectDAO.delete(project);
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) {
		return projectDAO.read(parameters);
	}

	@Override
	public List<Project> readAll() {
		return projectDAO.readAll();
	}
}

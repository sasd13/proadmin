package com.sasd13.proadmin.ws.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.update.ProjectUpdate;
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
	public void update(ProjectUpdate projectUpdate) {
		projectDAO.update(projectUpdate);
	}

	@Override
	public void delete(Project project) {
		projectDAO.delete(project);
	}

	@Override
	public List<Project> read(Map<String, String[]> criterias) {
		return projectDAO.read(criterias);
	}
}

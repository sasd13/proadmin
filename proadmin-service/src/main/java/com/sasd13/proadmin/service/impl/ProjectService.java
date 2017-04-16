package com.sasd13.proadmin.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.IProjectDAO;
import com.sasd13.proadmin.service.IProjectService;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;

public class ProjectService implements IProjectService {

	private IProjectDAO session;

	public ProjectService(DAO dao) {
		session = (IProjectDAO) dao.getSession(IProjectDAO.class);
	}

	@Override
	public long create(Project project) {
		return session.create(project);
	}

	@Override
	public void update(ProjectUpdateWrapper updateWrapper) {
		session.update(updateWrapper);
	}

	@Override
	public void delete(Project project) {
		session.delete(project);
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) {
		return session.read(parameters);
	}

	@Override
	public List<Project> readAll() {
		return session.readAll();
	}
}

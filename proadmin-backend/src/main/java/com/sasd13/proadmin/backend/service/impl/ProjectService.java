package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.dao.IProjectDAO;
import com.sasd13.proadmin.backend.model.Project;
import com.sasd13.proadmin.backend.service.IProjectService;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectDAO projectDAO;

	@Override
	public void create(Project project) {
		projectDAO.create(project);
	}

	@Override
	public void update(Project project) {
		projectDAO.update(project);
	}

	@Override
	public void delete(Project project) {
		projectDAO.delete(project);
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) {
		return projectDAO.read(parameters);
	}
}

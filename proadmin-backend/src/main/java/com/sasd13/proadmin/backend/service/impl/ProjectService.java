package com.sasd13.proadmin.backend.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IProjectDAO;
import com.sasd13.proadmin.backend.model.Project;
import com.sasd13.proadmin.backend.service.IProjectService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
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
	public List<Project> read(Map<String, Object> criterias) {
		return projectDAO.read(criterias);
	}
}

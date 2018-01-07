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
import com.sasd13.proadmin.backend.util.adapter.itf.ProjectITFAdapter;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectDAO projectDAO;

	private ProjectITFAdapter adapter;

	public ProjectService() {
		adapter = new ProjectITFAdapter();
	}

	@Override
	public void create(ProjectBean projectBean) {
		Project project = adapter.adaptI2M(projectBean);

		projectDAO.create(project);
	}

	@Override
	public void update(ProjectBean projectBean) {
		Project project = adapter.adaptI2M(projectBean);

		projectDAO.update(project);
	}

	@Override
	public void delete(ProjectBean projectBean) {
		Project project = adapter.adaptI2M(projectBean);

		projectDAO.delete(project);
	}

	@Override
	public List<ProjectBean> read(Map<String, Object> criterias) {
		List<Project> projects = projectDAO.read(criterias);

		return adapter.adaptM2I(projects);
	}
}

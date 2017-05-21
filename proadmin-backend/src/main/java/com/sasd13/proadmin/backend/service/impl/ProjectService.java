package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.proadmin.backend.dao.IProjectDAO;
import com.sasd13.proadmin.backend.model.Project;
import com.sasd13.proadmin.backend.service.IProjectService;
import com.sasd13.proadmin.backend.util.adapter.itf2model.ProjectAdapterI2M;
import com.sasd13.proadmin.backend.util.adapter.model2itf.ProjectAdapterM2I;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectDAO projectDAO;

	@Override
	public void create(ProjectBean projectBean) {
		Project project = adaptI2M(projectBean);

		projectDAO.create(project);
	}

	private Project adaptI2M(ProjectBean projectBean) {
		return new ProjectAdapterI2M().adapt(projectBean);
	}

	@Override
	public void update(ProjectBean projectBean) {
		Project project = adaptI2M(projectBean);

		projectDAO.update(project);
	}

	@Override
	public void delete(ProjectBean projectBean) {
		Project project = adaptI2M(projectBean);

		projectDAO.delete(project);
	}

	@Override
	public List<ProjectBean> read(Map<String, Object> criterias) {
		List<Project> projects = projectDAO.read(criterias);

		return adaptM2I(projects);
	}

	private List<ProjectBean> adaptM2I(List<Project> projects) {
		List<ProjectBean> list = new ArrayList<>();
		ProjectAdapterM2I adapter = new ProjectAdapterM2I();

		for (Project project : projects) {
			list.add(adapter.adapt(project));
		}

		return list;
	}
}

package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.dao.IProjectDAO;
import com.sasd13.proadmin.ws2.dao.dto.ProjectDTO;
import com.sasd13.proadmin.ws2.service.IProjectService;
import com.sasd13.proadmin.ws2.util.adapter.dto2bean.ProjectDTOAdapter;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectDAO projectDAO;

	@Override
	public void create(Project project) {
		projectDAO.create(project);
	}

	@Override
	public void update(List<ProjectUpdateWrapper> updateWrappers) {
		projectDAO.update(updateWrappers);
	}

	@Override
	public void delete(List<Project> projects) {
		projectDAO.delete(projects);
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) {
		List<Project> projects = new ArrayList<>();

		List<ProjectDTO> dtos = projectDAO.read(parameters);
		ProjectDTOAdapter adapter = new ProjectDTOAdapter();

		for (ProjectDTO dto : dtos) {
			projects.add(adapter.adapt(dto));
		}

		return projects;
	}
}

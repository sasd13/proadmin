package com.sasd13.proadmin.ws2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dao.IProjectDAO;
import com.sasd13.proadmin.ws2.db.dto.ProjectDTO;
import com.sasd13.proadmin.ws2.db.dto.adapter.ProjectDTOAdapter;
import com.sasd13.proadmin.ws2.service.IProjectService;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	private IProjectDAO projectDAO;

	@Override
	public void create(Project project) {
		projectDAO.create(project);
	}

	@Override
	public void update(IProjectUpdateWrapper updateWrapper) {
		projectDAO.update(updateWrapper);
	}

	@Override
	public void delete(Project project) {
		projectDAO.delete(project);
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

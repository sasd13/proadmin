package com.sasd13.proadmin.backend.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sasd13.proadmin.backend.bean.Project;
import com.sasd13.proadmin.backend.dao.IProjectDAO;
import com.sasd13.proadmin.backend.dao.dto.ProjectDTO;
import com.sasd13.proadmin.backend.service.IProjectService;
import com.sasd13.proadmin.backend.util.adapter.dto2bean.ProjectAdapterD2B;

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
		List<Project> list = new ArrayList<>();

		List<ProjectDTO> dtos = projectDAO.read(parameters);
		ProjectAdapterD2B adapter = new ProjectAdapterD2B();

		for (ProjectDTO dto : dtos) {
			list.add(adapter.adapt(dto));
		}

		return list;
	}
}

package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;

public interface IProjectService {

	long create(Project project);

	void update(ProjectUpdateWrapper updateWrapper);

	void delete(Project project);

	List<Project> read(Map<String, String[]> parameters);

	List<Project> readAll();
}

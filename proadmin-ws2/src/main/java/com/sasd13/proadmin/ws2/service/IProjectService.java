package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;

public interface IProjectService {

	void create(Project project);

	void update(List<ProjectUpdateWrapper> updateWrappers);

	void delete(List<Project> projects);

	List<Project> read(Map<String, String[]> parameters);
}

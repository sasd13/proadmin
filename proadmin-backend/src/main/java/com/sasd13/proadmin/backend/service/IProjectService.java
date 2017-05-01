package com.sasd13.proadmin.backend.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.model.Project;

public interface IProjectService {

	void create(Project project);

	void update(Project project);

	void delete(Project project);

	List<Project> read(Map<String, Object> criterias);
}

package com.sasd13.proadmin.ws2.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;

public interface IProjectService {

	void create(Project project);

	void update(IProjectUpdateWrapper updateWrapper);

	void delete(Project project);

	List<Project> read(Map<String, String[]> parameters);
}

package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.ws.bean.Project;
import com.sasd13.proadmin.ws.bean.update.ProjectUpdate;

public interface IProjectService {

	long create(Project project);

	void update(ProjectUpdate projectUpdate);

	void delete(Project project);

	List<Project> read(Map<String, String[]> criterias);
}

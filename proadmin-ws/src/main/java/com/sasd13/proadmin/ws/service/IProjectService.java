package com.sasd13.proadmin.ws.service;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.ws.bean.update.ProjectUpdate;

public interface IProjectService {

	long create(IProject iProject);

	void update(ProjectUpdate updateWrapper);

	void delete(IProject iProject);

	List<IProject> read(Map<String, String[]> parameters);

	List<IProject> readAll();
}

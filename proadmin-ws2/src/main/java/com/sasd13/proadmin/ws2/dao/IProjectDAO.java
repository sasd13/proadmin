package com.sasd13.proadmin.ws2.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.dao.dto.ProjectDTO;

public interface IProjectDAO {

	ProjectDTO create(Project project);

	void update(List<ProjectUpdateWrapper> updateWrappers);

	void delete(List<Project> projects);

	List<ProjectDTO> read(Map<String, String[]> parameters);
}

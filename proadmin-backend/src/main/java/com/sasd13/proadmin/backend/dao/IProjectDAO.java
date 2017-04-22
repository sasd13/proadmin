package com.sasd13.proadmin.backend.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.backend.bean.Project;
import com.sasd13.proadmin.backend.dao.dto.ProjectDTO;

public interface IProjectDAO {

	ProjectDTO create(Project project);

	void update(Project project);

	void delete(Project project);

	List<ProjectDTO> read(Map<String, String[]> parameters);
}

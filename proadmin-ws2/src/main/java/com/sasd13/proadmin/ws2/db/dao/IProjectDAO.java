package com.sasd13.proadmin.ws2.db.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.wrapper.update.project.IProjectUpdateWrapper;
import com.sasd13.proadmin.ws2.db.dto.ProjectDTO;

public interface IProjectDAO {

	ProjectDTO create(Project project);

	void update(IProjectUpdateWrapper updateWrapper);

	void delete(Project project);

	List<ProjectDTO> read(Map<String, String[]> parameters);
}

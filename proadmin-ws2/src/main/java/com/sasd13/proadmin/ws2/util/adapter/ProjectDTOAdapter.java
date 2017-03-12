package com.sasd13.proadmin.ws2.util.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.ws2.db.dto.ProjectDTO;

public class ProjectDTOAdapter implements IAdapter<ProjectDTO, Project> {

	@Override
	public Project adapt(ProjectDTO source) {
		Project target = new Project();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(ProjectDTO source, Project target) {
		target.setCode(source.getCode());
		target.setDateCreation(source.getDateCreation());
		target.setTitle(source.getTitle());
		target.setDescription(source.getDescription());
	}
}

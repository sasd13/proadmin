package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Project;
import com.sasd13.proadmin.backend.dao.dto.ProjectDTO;

public class ProjectAdapterD2B implements IAdapter<ProjectDTO, Project> {

	@Override
	public Project adapt(ProjectDTO s) {
		Project t = new Project();

		t.setCode(s.getCode());
		t.setDateCreation(s.getDateCreation());
		t.setTitle(s.getTitle());
		t.setDescription(s.getDescription());

		return t;
	}
}

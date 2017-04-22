package com.sasd13.proadmin.backend.util.adapter.bean2dto;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.Project;
import com.sasd13.proadmin.backend.dao.dto.ProjectDTO;

public class ProjectAdapterB2D implements IAdapter<Project, ProjectDTO> {

	@Override
	public ProjectDTO adapt(Project s) {
		ProjectDTO t = new ProjectDTO();

		t.setId(s.getId());
		t.setCode(s.getCode());
		t.setDateCreation(s.getDateCreation());
		t.setTitle(s.getTitle());
		t.setDescription(s.getDescription());

		return t;
	}
}

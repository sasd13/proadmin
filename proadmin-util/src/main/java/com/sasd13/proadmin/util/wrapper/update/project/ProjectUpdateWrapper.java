package com.sasd13.proadmin.util.wrapper.update.project;

import com.sasd13.proadmin.bean.project.Project;

public class ProjectUpdateWrapper implements IProjectUpdateWrapper {

	private Project project;
	private String code;

	@Override
	public Project getWrapped() {
		return project;
	}

	@Override
	public void setWrapped(Project project) {
		this.project = project;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}
}

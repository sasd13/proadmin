package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.project.Project;

public class ProjectDecorator extends Project {
	
	private Project project;
	private boolean deleted;
	
	public ProjectDecorator(Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}

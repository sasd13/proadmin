package com.sasd13.proadmin.util.builder.project;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.project.Project;

public class ProjectBaseBuilder implements IBuilder<Project> {

	private String code;

	public ProjectBaseBuilder(String code) {
		this.code = code;
	}

	@Override
	public Project build() {
		Project project = new Project();
		project.setCode(code);

		return project;
	}
}

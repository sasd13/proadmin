package com.sasd13.proadmin.util.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.project.Project;

public class ProjectCodeCriteria implements Criteria<Project> {

	private String code;

	public ProjectCodeCriteria(String code) {
		this.code = code;
	}

	@Override
	public List<Project> meetCriteria(List<Project> list) {
		List<Project> results = new ArrayList<Project>();

		for (Project project : list) {
			if (project.getCode().equalsIgnoreCase(code)) {
				results.add(project);
			}
		}

		return results;
	}
}

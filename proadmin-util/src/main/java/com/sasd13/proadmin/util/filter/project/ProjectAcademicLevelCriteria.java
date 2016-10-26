package com.sasd13.proadmin.util.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.project.Project;

public class ProjectAcademicLevelCriteria implements Criteria<Project> {

	private EnumAcademicLevel academicLevel;

	public ProjectAcademicLevelCriteria(EnumAcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	@Override
	public List<Project> meetCriteria(List<Project> list) {
		List<Project> results = new ArrayList<Project>();

		for (Project project : list) {
			if (academicLevel == project.getAcademicLevel()) {
				results.add(project);
			}
		}

		return results;
	}
}

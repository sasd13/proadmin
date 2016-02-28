package com.sasd13.proadmin.core.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.project.Project;

public class AcademicLevelCriteria implements Criteria<Project> {
	
	private String academicLevel;
	
	public AcademicLevelCriteria(String academicLevel) {
		this.academicLevel = academicLevel;
	}
	
	@Override
	public List<Project> meetCriteria(List<Project> entities) {
		List<Project> result = new ArrayList<Project>();
		
		for (Project project : entities) {
			if (academicLevel.equalsIgnoreCase(String.valueOf(project.getAcademicLevel()))) {
				result.add(project);
			}
		}
		
		return result;
	}
}

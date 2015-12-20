package com.sasd13.proadmin.core.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.project.Project;

public class AcademicLevelCriteria implements Criteria<Project> {
	
	private AcademicLevel academicLevel;
	
	public AcademicLevelCriteria(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	@Override
	public List<Project> meetCriteria(List<Project> entities) {
		List<Project> result = new ArrayList<Project>();
		
		for (Project project : entities) {
			if (academicLevel.equals(project.getAcademicLevel())) {
				result.add(project);
			}
		}
		
		return result;
	}
}

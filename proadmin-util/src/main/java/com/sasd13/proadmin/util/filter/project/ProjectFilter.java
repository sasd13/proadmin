package com.sasd13.proadmin.util.filter.project;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.EnumParameter;

public class ProjectFilter extends AndFilter<Project> {

	public ProjectFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(Map<String, String[]> parameters, MultiAndCriteria<Project> multiAndCriteria) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new ProjectAcademicLevelCriteria(EnumAcademicLevel.find(value)));
				} else if (EnumParameter.CODE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new ProjectCodeCriteria(value));
				} else if (EnumParameter.TITLE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new ProjectTitleCriteria(value));
				}
			}
		}
	}
}

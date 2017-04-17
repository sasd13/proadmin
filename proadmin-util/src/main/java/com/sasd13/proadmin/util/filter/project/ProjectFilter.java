package com.sasd13.proadmin.util.filter.project;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.EnumParameter;

public class ProjectFilter extends AndFilter<Project> {

	public ProjectFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<Project> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.CODE.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ProjectCodeCriteria(value));
				}
			}
		}
	}
}

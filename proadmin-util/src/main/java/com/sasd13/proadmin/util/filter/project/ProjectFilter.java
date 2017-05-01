package com.sasd13.proadmin.util.filter.project;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.util.EnumCriteria;

public class ProjectFilter extends AndFilter<IProject> {

	public ProjectFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<IProject> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumCriteria.CODE.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ProjectCodeCriteria(value));
				}
			}
		}
	}
}

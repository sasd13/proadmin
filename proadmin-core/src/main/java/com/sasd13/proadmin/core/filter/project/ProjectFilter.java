package com.sasd13.proadmin.core.filter.project;

import java.util.Map;

import com.sasd13.javaex.net.util.URLParameterFilter;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.util.Parameter;

public class ProjectFilter extends URLParameterFilter<Project> {
	
	public ProjectFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.CODE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				} else if (Parameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(value));
				} else if (Parameter.TITLE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new TitleCriteria(value));
				}
			}
		}
	}
}

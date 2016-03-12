package com.sasd13.proadmin.util.filter.project;

import java.util.Map;

import com.sasd13.javaex.net.util.URLParameterFilter;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.Parameter;

public class ProjectFilter extends URLParameterFilter<Project> {
	
	public ProjectFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.CODE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				} else if (Parameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new AcademicLevelCriteria(AcademicLevel.valueOf(value)));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					}
				} else if (Parameter.TITLE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new TitleCriteria(value));
				}
			}
		}
	}
}

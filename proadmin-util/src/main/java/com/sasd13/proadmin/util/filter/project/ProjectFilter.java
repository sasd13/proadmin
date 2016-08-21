package com.sasd13.proadmin.util.filter.project;

import java.util.Map;

import com.sasd13.javaex.net.util.URLQueryFilter;
import com.sasd13.proadmin.bean.EnumAcademicLevel;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.EnumParameter;

public class ProjectFilter extends URLQueryFilter<Project> {
	
	public ProjectFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new AcademicLevelCriteria(EnumAcademicLevel.find(value)));
				} else if (EnumParameter.CODE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				} else if (EnumParameter.TITLE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new TitleCriteria(value));
				}
			}
		}
	}
}

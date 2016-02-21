package com.sasd13.proadmin.core.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.project.Project;

public class CodeCriteria implements Criteria<Project> {
	
	private String code;
	
	public CodeCriteria(String code) {
		this.code = code;
	}
	
	@Override
	public List<Project> meetCriteria(List<Project> entities) {
		List<Project> result = new ArrayList<Project>();
		
		for (Project project : entities) {
			if (code.equalsIgnoreCase(project.getCode())) {
				result.add(project);
			}
		}
		
		return result;
	}
}

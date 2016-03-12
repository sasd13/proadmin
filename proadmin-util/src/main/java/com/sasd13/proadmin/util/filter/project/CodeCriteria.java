package com.sasd13.proadmin.util.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.project.Project;

public class CodeCriteria implements Criteria<Project> {
	
	private String code;
	
	public CodeCriteria(String code) {
		this.code = code;
	}
	
	@Override
	public List<Project> meetCriteria(List<Project> list) {
		List<Project> results = new ArrayList<Project>();
		
		for (Project project : list) {
			if (project.getCode().equalsIgnoreCase(code)) {
				results.add(project);
			}
		}
		
		return results;
	}
}

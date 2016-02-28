package com.sasd13.proadmin.core.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.project.Project;

public class TitleCriteria implements Criteria<Project> {
	
	private String title;
	
	public TitleCriteria(String title) {
		this.title = title;
	}
	
	@Override
	public List<Project> meetCriteria(List<Project> entities) {
		List<Project> result = new ArrayList<Project>();
		
		for (Project project : entities) {
			if (title.equalsIgnoreCase(project.getTitle())) {
				result.add(project);
			}
		}
		
		return result;
	}
}

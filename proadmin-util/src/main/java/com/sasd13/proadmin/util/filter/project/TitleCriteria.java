package com.sasd13.proadmin.util.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.project.Project;

public class TitleCriteria implements Criteria<Project> {
	
	private String title;
	
	public TitleCriteria(String title) {
		this.title = title;
	}
	
	@Override
	public List<Project> meetCriteria(List<Project> list) {
		List<Project> results = new ArrayList<Project>();
		
		for (Project project : list) {
			if (title.equalsIgnoreCase(project.getTitle())) {
				results.add(project);
			}
		}
		
		return results;
	}
}

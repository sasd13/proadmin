package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.running.Running;

public class ProjectCriteria implements Criteria<Running> {
	
	private long id;
	
	public ProjectCriteria(long id) {
		this.id = id;
	}
	
	@Override
	public List<Running> meetCriteria(List<Running> list) {
		List<Running> results = new ArrayList<Running>();
		
		for (Running running : list) {
			if (id == running.getProject().getId()) {
				results.add(running);
			}
		}
		
		return results;
	}
}

package com.sasd13.proadmin.core.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.running.Running;

public class ProjectCriteria implements Criteria<Running> {
	
	private long id;
	
	public ProjectCriteria(long id) {
		this.id = id;
	}
	
	@Override
	public List<Running> meetCriteria(List<Running> entities) {
		List<Running> result = new ArrayList<Running>();
		
		for (Running running : entities) {
			if (id == running.getProject().getId()) {
				result.add(running);
			}
		}
		
		return result;
	}
	
}

package com.sasd13.proadmin.core.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.core.bean.running.Running;

public class TeacherCriteria implements Criteria<Running> {

	private long id;
	
	public TeacherCriteria(long id) {
		this.id = id;
	}
	
	@Override
	public List<Running> meetCriteria(List<Running> entities) {
		List<Running> result = new ArrayList<Running>();
		
		for (Running running : entities) {
			if (id == running.getTeacher().getId()) {
				result.add(running);
			}
		}
		
		return result;
	}

}

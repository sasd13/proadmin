package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Running;

public class RunningTeacherIdCriteria implements Criteria<Running> {

	private long id;

	public RunningTeacherIdCriteria(long id) {
		this.id = id;
	}

	@Override
	public List<Running> meetCriteria(List<Running> list) {
		List<Running> results = new ArrayList<Running>();

		for (Running running : list) {
			if (id == running.getTeacher().getId()) {
				results.add(running);
			}
		}

		return results;
	}
}

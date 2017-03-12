package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Running;

public class RunningTeacherCriteria implements Criteria<Running> {

	private String number;

	public RunningTeacherCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<Running> meetCriteria(List<Running> list) {
		List<Running> results = new ArrayList<Running>();

		for (Running running : list) {
			if (running.getTeacher().getNumber().equalsIgnoreCase(number)) {
				results.add(running);
			}
		}

		return results;
	}
}

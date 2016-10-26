package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Running;

public class RunningYearCriteria implements Criteria<Running> {

	private int year;

	public RunningYearCriteria(int year) {
		this.year = year;
	}

	@Override
	public List<Running> meetCriteria(List<Running> list) {
		List<Running> results = new ArrayList<Running>();

		for (Running running : list) {
			if (year == running.getYear()) {
				results.add(running);
			}
		}

		return results;
	}
}

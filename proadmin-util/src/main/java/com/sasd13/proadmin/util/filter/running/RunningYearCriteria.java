package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IRunning;

public class RunningYearCriteria implements Criteria<IRunning> {

	private int year;

	public RunningYearCriteria(int year) {
		this.year = year;
	}

	@Override
	public List<IRunning> meetCriteria(List<IRunning> list) {
		List<IRunning> results = new ArrayList<IRunning>();

		for (IRunning item : list) {
			if (year == item.getYear()) {
				results.add(item);
			}
		}

		return results;
	}
}

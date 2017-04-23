package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IReport;

public class ReportYearCriteria implements Criteria<IReport> {

	private int year;

	public ReportYearCriteria(int year) {
		this.year = year;
	}

	@Override
	public List<IReport> meetCriteria(List<IReport> list) {
		List<IReport> results = new ArrayList<IReport>();

		for (IReport item : list) {
			if (year == item.getRunningTeam().getRunning().getYear()) {
				results.add(item);
			}
		}

		return results;
	}
}

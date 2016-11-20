package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class ReportYearCriteria implements Criteria<Report> {

	private int year;

	public ReportYearCriteria(int year) {
		this.year = year;
	}

	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();

		for (Report report : list) {
			if (year == report.getRunningTeam().getRunning().getYear()) {
				results.add(report);
			}
		}

		return results;
	}
}

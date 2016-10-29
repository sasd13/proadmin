package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class ReportTeamCriteria implements Criteria<Report> {

	private String number;

	public ReportTeamCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();

		for (Report report : list) {
			if (number.equalsIgnoreCase(report.getRunningTeam().getTeam().getNumber())) {
				results.add(report);
			}
		}

		return results;
	}
}

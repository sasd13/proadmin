package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class ReportProjectCriteria implements Criteria<Report> {

	private String code;

	public ReportProjectCriteria(String code) {
		this.code = code;
	}

	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();

		for (Report report : list) {
			if (report.getRunningTeam().getRunning().getProject().getCode().equalsIgnoreCase(code)) {
				results.add(report);
			}
		}

		return results;
	}
}

package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class ReportNumberCriteria implements Criteria<Report> {

	private String number;

	public ReportNumberCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();

		for (Report report : list) {
			if (report.getNumber().equalsIgnoreCase(number)) {
				results.add(report);
			}
		}

		return results;
	}
}

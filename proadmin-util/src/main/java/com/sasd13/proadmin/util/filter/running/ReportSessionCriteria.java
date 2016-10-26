package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class ReportSessionCriteria implements Criteria<Report> {

	private int session;

	public ReportSessionCriteria(int session) {
		this.session = session;
	}

	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();

		for (Report report : list) {
			if (session == report.getSession()) {
				results.add(report);
			}
		}

		return results;
	}
}

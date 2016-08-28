package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class SessionNumberCriteria implements Criteria<Report> {

	private int sessionNumber;

	public SessionNumberCriteria(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();

		for (Report report : list) {
			if (sessionNumber == report.getSessionNumber()) {
				results.add(report);
			}
		}

		return results;
	}
}

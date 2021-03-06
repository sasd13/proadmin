package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IReport;

public class ReportSessionCriteria implements Criteria<IReport> {

	private int session;

	public ReportSessionCriteria(int session) {
		this.session = session;
	}

	@Override
	public List<IReport> meetCriteria(List<IReport> list) {
		List<IReport> results = new ArrayList<IReport>();

		for (IReport item : list) {
			if (session == item.getSession()) {
				results.add(item);
			}
		}

		return results;
	}
}

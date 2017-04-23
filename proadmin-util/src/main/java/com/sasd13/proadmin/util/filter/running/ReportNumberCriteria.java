package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IReport;

public class ReportNumberCriteria implements Criteria<IReport> {

	private String number;

	public ReportNumberCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<IReport> meetCriteria(List<IReport> list) {
		List<IReport> results = new ArrayList<IReport>();

		for (IReport iReport : list) {
			if (iReport.getNumber().equalsIgnoreCase(number)) {
				results.add(iReport);
			}
		}

		return results;
	}
}

package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IReport;

public class ReportProjectCriteria implements Criteria<IReport> {

	private String code;

	public ReportProjectCriteria(String code) {
		this.code = code;
	}

	@Override
	public List<IReport> meetCriteria(List<IReport> list) {
		List<IReport> results = new ArrayList<IReport>();

		for (IReport item : list) {
			if (item.getRunningTeam().getRunning().getProject().getCode().equalsIgnoreCase(code)) {
				results.add(item);
			}
		}

		return results;
	}
}

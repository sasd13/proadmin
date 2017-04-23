package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IReport;

public class ReportTeacherCriteria implements Criteria<IReport> {

	private String intermediary;

	public ReportTeacherCriteria(String intermediary) {
		this.intermediary = intermediary;
	}

	@Override
	public List<IReport> meetCriteria(List<IReport> list) {
		List<IReport> results = new ArrayList<IReport>();

		for (IReport iReport : list) {
			if (iReport.getRunningTeam().getRunning().getTeacher().getIntermediary().equalsIgnoreCase(intermediary)) {
				results.add(iReport);
			}
		}

		return results;
	}
}

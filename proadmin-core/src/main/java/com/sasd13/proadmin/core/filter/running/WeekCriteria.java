package com.sasd13.proadmin.core.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.running.Report;

public class WeekCriteria implements Criteria<Report> {
	
	private int week;
	
	public WeekCriteria(int week) {
		this.week = week;
	}
	
	@Override
	public List<Report> meetCriteria(List<Report> entities) {
		List<Report> result = new ArrayList<Report>();
		
		for (Report report : entities) {
			if (week == report.getWeek()) {
				result.add(report);
			}
		}
		
		return result;
	}
	
}

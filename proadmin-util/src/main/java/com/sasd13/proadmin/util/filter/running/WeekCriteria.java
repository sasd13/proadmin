package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class WeekCriteria implements Criteria<Report> {
	
	private int week;
	
	public WeekCriteria(int week) {
		this.week = week;
	}
	
	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();
		
		for (Report report : list) {
			if (report.getWeek() == week) {
				results.add(report);
			}
		}
		
		return results;
	}
}

package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class TeamCriteria implements Criteria<Report> {
	
	private long id;
	
	public TeamCriteria(long id) {
		this.id = id;
	}
	
	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();
		
		for (Report report : list) {
			if (report.getTeam().getId() == id) {
				results.add(report);
			}
		}
		
		return results;
	}
}

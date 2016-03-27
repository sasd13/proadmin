package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.bean.running.Report;

public class RunningTeamCriteria implements Criteria<Report> {
	
	private long id;
	
	public RunningTeamCriteria(long id) {
		this.id = id;
	}
	
	@Override
	public List<Report> meetCriteria(List<Report> list) {
		List<Report> results = new ArrayList<Report>();
		
		for (Report report : list) {
			if (id == report.getRunningTeam().getId()) {
				results.add(report);
			}
		}
		
		return results;
	}
}

package com.sasd13.proadmin.core.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.running.Report;

public class TeamCriteria implements Criteria<Report> {
	
	private long id;
	
	public TeamCriteria(long id) {
		this.id = id;
	}
	
	@Override
	public List<Report> meetCriteria(List<Report> entities) {
		List<Report> result = new ArrayList<Report>();
		
		for (Report report : entities) {
			if (id == report.getTeam().getId()) {
				result.add(report);
			}
		}
		
		return result;
	}
}

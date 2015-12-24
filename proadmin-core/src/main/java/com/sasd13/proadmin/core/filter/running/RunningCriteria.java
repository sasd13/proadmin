package com.sasd13.proadmin.core.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.criteria.Criteria;
import com.sasd13.proadmin.core.bean.running.Team;

public class RunningCriteria implements Criteria<Team> {
	
	private long id;
	
	public RunningCriteria(long id) {
		this.id = id;
	}
	
	@Override
	public List<Team> meetCriteria(List<Team> entities) {
		List<Team> result = new ArrayList<Team>();
		
		for (Team team : entities) {
			if (id == team.getRunning().getId()) {
				result.add(team);
			}
		}
		
		return result;
	}
}

package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.criteria.AndFilter;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.Parameter;

public class RunningTeamFilter extends AndFilter<RunningTeam> {
	
	public RunningTeamFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.RUNNING.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new RunningCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						//throw new FilterException
					}
				} else if (Parameter.TEAM.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new TeamCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						//throw new FilterException
					}
				}
			}
		}
	}
}

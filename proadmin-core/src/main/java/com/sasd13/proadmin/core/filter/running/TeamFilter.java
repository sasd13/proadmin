package com.sasd13.proadmin.core.filter.running;

import java.util.Map;

import com.sasd13.javaex.util.ParameterFilter;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.util.Parameter;

public class TeamFilter extends ParameterFilter<Team> {
	
	public TeamFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.CODE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				} else if (Parameter.RUNNING.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new RunningCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

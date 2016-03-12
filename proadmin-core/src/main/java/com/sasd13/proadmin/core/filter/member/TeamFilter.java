package com.sasd13.proadmin.core.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.criteria.AndFilter;
import com.sasd13.proadmin.core.bean.member.Team;
import com.sasd13.proadmin.core.util.Parameter;

public class TeamFilter extends AndFilter<Team> {
	
	public TeamFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.CODE.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new CodeCriteria(value));
				}
			}
		}
	}
}

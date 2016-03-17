package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.criteria.AndFilter;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.Parameter;

public class StudentTeamFilter extends AndFilter<StudentTeam> {
	
	public StudentTeamFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.STUDENT.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new StudentCriteria(Long.parseLong(value)));
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

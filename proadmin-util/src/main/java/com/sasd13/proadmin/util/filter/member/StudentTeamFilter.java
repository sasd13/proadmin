package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentTeamFilter extends AndFilter<StudentTeam> {

	public StudentTeamFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(Map<String, String[]> parameters, MultiAndCriteria<StudentTeam> multiAndCriteria) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.STUDENT.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new StudentTeamStudentCriteria(value));
				} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new StudentTeamTeamCriteria(value));
				}
			}
		}
	}
}

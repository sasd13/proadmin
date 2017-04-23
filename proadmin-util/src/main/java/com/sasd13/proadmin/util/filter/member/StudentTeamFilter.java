package com.sasd13.proadmin.util.filter.member;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.member.IStudentTeam;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentTeamFilter extends AndFilter<IStudentTeam> {

	public StudentTeamFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<IStudentTeam> multiAndCriteria, Map<String, String[]> parameters) {
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

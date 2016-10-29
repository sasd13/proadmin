package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamFilter extends AndFilter<RunningTeam> {

	public RunningTeamFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(Map<String, String[]> parameters, MultiAndCriteria<RunningTeam> multiAndCriteria) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.PROJECT.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamProjectCriteria(value));
				} else if (EnumParameter.TEACHER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamTeacherCriteria(value));
				} else if (EnumParameter.TEAM.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamTeamCriteria(value));
				} else if (EnumParameter.ACADEMICLEVEL.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamAcademicLevelCriteria(value));
				}
			}
		}
	}
}

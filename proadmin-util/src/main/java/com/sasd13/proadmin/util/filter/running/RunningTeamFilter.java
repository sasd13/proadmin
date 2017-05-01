package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.IRunningTeam;
import com.sasd13.proadmin.util.EnumCriteria;

public class RunningTeamFilter extends AndFilter<IRunningTeam> {

	public RunningTeamFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<IRunningTeam> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamYearCriteria(Integer.parseInt(value)));
				} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamProjectCriteria(value));
				} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamTeacherCriteria(value));
				} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamTeamCriteria(value));
				} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamAcademicLevelCriteria(value));
				}
			}
		}
	}
}

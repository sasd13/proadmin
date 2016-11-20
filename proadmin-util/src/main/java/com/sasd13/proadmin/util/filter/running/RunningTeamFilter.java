package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.FilterException;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamFilter extends AndFilter<RunningTeam> {

	public RunningTeamFilter(Map<String, String[]> parameters) throws FilterException {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<RunningTeam> multiAndCriteria, Map<String, String[]> parameters) throws FilterException {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.YEAR.getName().equalsIgnoreCase(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new RunningTeamYearCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						throw new FilterException("RunningTeam filter : year criteria parsing '" + value + "' error");
					}
				} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamProjectCriteria(value));
				} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamTeacherCriteria(value));
				} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamTeamCriteria(value));
				} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeamAcademicLevelCriteria(value));
				}
			}
		}
	}
}

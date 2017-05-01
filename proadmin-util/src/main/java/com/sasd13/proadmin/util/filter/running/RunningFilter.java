package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.util.EnumCriteria;

public class RunningFilter extends AndFilter<IRunning> {

	public RunningFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<IRunning> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningYearCriteria(Integer.parseInt(value)));
				} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningProjectCriteria(value));
				} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeacherCriteria(value));
				}
			}
		}
	}
}

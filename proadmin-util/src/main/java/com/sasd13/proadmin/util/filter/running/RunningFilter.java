package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningFilter extends AndFilter<Running> {

	public RunningFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(Map<String, String[]> parameters, MultiAndCriteria<Running> multiAndCriteria) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.YEAR.getName().equalsIgnoreCase(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new RunningYearCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningProjectCriteria(value));
				} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new RunningTeacherCriteria(value));
				}
			}
		}
	}
}

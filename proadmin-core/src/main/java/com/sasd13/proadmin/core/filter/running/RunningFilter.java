package com.sasd13.proadmin.core.filter.running;

import java.util.Map;

import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.util.Parameter;

public class RunningFilter extends com.sasd13.javaex.util.ParameterFilter<Running> {
	
	public RunningFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.YEAR.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new YearCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (Parameter.TEACHER.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new TeacherCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (Parameter.PROJECT.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new ProjectCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

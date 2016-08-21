package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.criteria.AndFilter;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningFilter extends AndFilter<Running> {
	
	public RunningFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.YEAR.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new YearCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (EnumParameter.TEACHER.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new TeacherCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (EnumParameter.PROJECT.getName().equals(entry.getKey())) {
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

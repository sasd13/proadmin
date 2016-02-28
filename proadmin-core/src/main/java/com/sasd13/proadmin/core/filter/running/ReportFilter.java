package com.sasd13.proadmin.core.filter.running;

import java.util.Map;

import com.sasd13.javaex.util.ParameterFilter;
import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.util.Parameter;

public class ReportFilter extends ParameterFilter<Report> {
	
	public ReportFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.WEEK.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new WeekCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (Parameter.TEAM.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new TeamCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

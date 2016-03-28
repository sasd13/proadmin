package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.criteria.AndFilter;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.Parameter;

public class ReportFilter extends AndFilter<Report> {
	
	public ReportFilter(Map<String, String[]> parameters) {
		super(parameters);
		
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (Parameter.SESSIONNUMBER.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new SessionNumberCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (Parameter.RUNNINGTEAM.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new RunningTeamCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

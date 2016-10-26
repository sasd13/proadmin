package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportFilter extends AndFilter<Report> {

	public ReportFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(Map<String, String[]> parameters, MultiAndCriteria<Report> multiAndCriteria) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.NUMBER.getName().equals(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportNumberCriteria(value));
				} else if (EnumParameter.SESSION.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new ReportSessionCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				} else if (EnumParameter.RUNNINGTEAM.getName().equals(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new ReportRunningTeamIdCriteria(Long.parseLong(value)));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}

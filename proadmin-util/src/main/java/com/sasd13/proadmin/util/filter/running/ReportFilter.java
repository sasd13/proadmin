package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.FilterException;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportFilter extends AndFilter<Report> {

	public ReportFilter(Map<String, String[]> parameters) throws FilterException {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<Report> multiAndCriteria, Map<String, String[]> parameters) throws FilterException {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.NUMBER.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportNumberCriteria(value));
				} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new ReportSessionCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						throw new FilterException("Report filter : session criteria parsing '" + value + "' error");
					}
				} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(entry.getKey())) {
					try {
						multiAndCriteria.addCriteria(new ReportYearCriteria(Integer.parseInt(value)));
					} catch (NumberFormatException e) {
						throw new FilterException("Report filter : year criteria parsing '" + value + "' error");
					}
				} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportProjectCriteria(value));
				} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportTeacherCriteria(value));
				} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportTeamCriteria(value));
				} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportAcademicLevelCriteria(value));
				}
			}
		}
	}
}

package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportFilter extends AndFilter<IReport> {

	public ReportFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<IReport> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumParameter.NUMBER.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportNumberCriteria(value));
				} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportSessionCriteria(Integer.parseInt(value)));
				} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportYearCriteria(Integer.parseInt(value)));
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

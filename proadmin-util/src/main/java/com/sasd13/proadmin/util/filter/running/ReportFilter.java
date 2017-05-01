package com.sasd13.proadmin.util.filter.running;

import java.util.Map;

import com.sasd13.javaex.pattern.filter.AndFilter;
import com.sasd13.javaex.pattern.filter.MultiAndCriteria;
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.util.EnumCriteria;

public class ReportFilter extends AndFilter<IReport> {

	public ReportFilter(Map<String, String[]> parameters) {
		super(parameters);
	}

	@Override
	protected void setCriterias(MultiAndCriteria<IReport> multiAndCriteria, Map<String, String[]> parameters) {
		for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
			for (String value : entry.getValue()) {
				if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportNumberCriteria(value));
				} else if (EnumCriteria.SESSION.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportSessionCriteria(Integer.parseInt(value)));
				} else if (EnumCriteria.YEAR.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportYearCriteria(Integer.parseInt(value)));
				} else if (EnumCriteria.PROJECT.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportProjectCriteria(value));
				} else if (EnumCriteria.TEACHER.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportTeacherCriteria(value));
				} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportTeamCriteria(value));
				} else if (EnumCriteria.ACADEMICLEVEL.getCode().equalsIgnoreCase(entry.getKey())) {
					multiAndCriteria.addCriteria(new ReportAcademicLevelCriteria(value));
				}
			}
		}
	}
}

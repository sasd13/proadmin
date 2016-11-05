package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
			try {
				return IReportDAO.COLUMN_SESSION + " = " + Integer.parseInt(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			try {
				return IReportDAO.COLUMN_RUNNINGTEAM_TEAM_CODE + " = " + Integer.parseInt(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_RUNNING_PROJECT_CODE + " = '" + value + "'";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_RUNNING_TEACHER_CODE + " = '" + value + "'";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_TEAM_CODE + " = '" + value + "'";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IReportDAO.COLUMN_RUNNINGTEAM_ACADEMICLEVEL_CODE + " = '" + value + "'";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}

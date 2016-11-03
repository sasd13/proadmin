package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_RUNNING_YEAR + " = '" + value + "'";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_RUNNING_PROJECT_CODE + " = '" + value + "'";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_RUNNING_TEACHER_CODE + " = " + value;
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_TEAM_CODE + " = " + value;
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IRunningTeamDAO.COLUMN_ACADEMICLEVEL_CODE + " = '" + value + "'";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}

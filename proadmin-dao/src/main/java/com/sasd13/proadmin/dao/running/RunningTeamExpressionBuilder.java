package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return IRunningTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.RUNNING.getName().equalsIgnoreCase(key)) {
				return IRunningTeamDAO.COLUMN_RUNNING_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
				return IRunningTeamDAO.COLUMN_TEAM_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("RunningTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("RunningTeam key '" + key + "' parameter parsing error");
		}
	}
}

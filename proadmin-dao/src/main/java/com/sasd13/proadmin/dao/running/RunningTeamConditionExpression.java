package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.condition.ConditionBuilderException;
import com.sasd13.javaex.dao.condition.IConditionExpression;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamConditionExpression implements IConditionExpression {

	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return IRunningTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.RUNNING.getName().equalsIgnoreCase(key)) {
				return IRunningTeamDAO.COLUMN_RUNNING_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
				return IRunningTeamDAO.COLUMN_TEAM_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionBuilderException("RunningTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionBuilderException("RunningTeam key '" + key + "' parameter parsing error");
		}
	}
}

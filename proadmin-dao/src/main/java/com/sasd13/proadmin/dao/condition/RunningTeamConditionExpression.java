package com.sasd13.proadmin.dao.condition;

import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.javaex.db.condition.IConditionExpression;
import com.sasd13.proadmin.dao.RunningTeamDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamConditionExpression implements IConditionExpression {

	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.RUNNING.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_RUNNING_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_TEAM_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionBuilderException("RunningTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionBuilderException("RunningTeam key '" + key + "' parameter parsing error");
		}
	}
}

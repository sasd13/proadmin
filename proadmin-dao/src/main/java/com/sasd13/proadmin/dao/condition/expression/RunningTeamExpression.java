package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.RunningTeamDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class RunningTeamExpression implements IExpression {
	
	public static final RunningTeamExpression INSTANCE = new RunningTeamExpression();
	
	private RunningTeamExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.RUNNING.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_RUNNING_ID + " = " + Long.parseLong(value);
			} else if (Parameter.TEAM.getName().equalsIgnoreCase(key)) {
				return RunningTeamDAO.COLUMN_TEAM_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("RunningTeam key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("RunningTeam key '" + key + "' parameter parsing error");
		}
	}
}

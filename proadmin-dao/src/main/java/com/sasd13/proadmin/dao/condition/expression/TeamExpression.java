package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.TeamDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class TeamExpression implements IExpression {
	
	public static final TeamExpression INSTANCE = new TeamExpression();
	
	private TeamExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		if (Parameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return TeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Team key '" + key + "' parameter parsing error");
			}
		} else if (Parameter.CODE.getName().equalsIgnoreCase(key)) {
			return TeamDAO.COLUMN_CODE + " = '" + value + "'";
		} else {
			throw new ConditionException("Team key '" + key + "' is not a declared parameter");
		}
	}
}

package com.sasd13.proadmin.dao.condition;

import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.javaex.db.condition.IConditionExpression;
import com.sasd13.proadmin.dao.TeamDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class TeamConditionExpression implements IConditionExpression {
	
	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return TeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionBuilderException("Team key '" + key + "' parameter parsing error");
			}
		} else if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return TeamDAO.COLUMN_CODE + " = '" + value + "'";
		} else {
			throw new ConditionBuilderException("Team key '" + key + "' is not a declared parameter");
		}
	}
}

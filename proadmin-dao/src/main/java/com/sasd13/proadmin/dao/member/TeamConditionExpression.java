package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.condition.ConditionBuilderException;
import com.sasd13.javaex.dao.condition.IConditionExpression;
import com.sasd13.proadmin.util.EnumParameter;

public class TeamConditionExpression implements IConditionExpression {

	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return ITeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionBuilderException("Team key '" + key + "' parameter parsing error");
			}
		} else if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return ITeamDAO.COLUMN_CODE + " = '" + value + "'";
		} else {
			throw new ConditionBuilderException("Team key '" + key + "' is not a declared parameter");
		}
	}
}

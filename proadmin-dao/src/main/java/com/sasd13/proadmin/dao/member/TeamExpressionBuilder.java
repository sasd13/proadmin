package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class TeamExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return ITeamDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Team key '" + key + "' parameter parsing error");
			}
		} else if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return ITeamDAO.COLUMN_CODE + " = '" + value + "'";
		} else {
			throw new ConditionException("Team key '" + key + "' is not a declared parameter");
		}
	}
}

package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class TeamExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return ITeamDAO.COLUMN_CODE + " = '" + value + "'";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}

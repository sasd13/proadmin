package com.sasd13.proadmin.dao.project;

import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class ProjectExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (EnumParameter.TITLE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_TITLE + " = '" + value + "'";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}

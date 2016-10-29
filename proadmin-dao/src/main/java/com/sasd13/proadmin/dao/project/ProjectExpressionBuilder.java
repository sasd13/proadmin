package com.sasd13.proadmin.dao.project;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class ProjectExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return IProjectDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Project key '" + key + "' parameter parsing error");
			}
		} else if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (EnumParameter.TITLE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_TITLE + " = '" + value + "'";
		} else {
			throw new ConditionException("Project key '" + key + "' is not a declared parameter");
		}
	}
}

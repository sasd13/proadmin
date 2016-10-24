package com.sasd13.proadmin.dao.project;

import com.sasd13.javaex.dao.condition.ConditionBuilderException;
import com.sasd13.javaex.dao.condition.IConditionExpression;
import com.sasd13.proadmin.util.EnumParameter;

public class ProjectConditionExpression implements IConditionExpression {

	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return IProjectDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionBuilderException("Project key '" + key + "' parameter parsing error");
			}
		} else if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (EnumParameter.TITLE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_TITLE + " = '" + value + "'";
		} else {
			throw new ConditionBuilderException("Project key '" + key + "' is not a declared parameter");
		}
	}
}

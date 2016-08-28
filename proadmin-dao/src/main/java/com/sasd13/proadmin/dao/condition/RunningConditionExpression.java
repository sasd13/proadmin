package com.sasd13.proadmin.dao.condition;

import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.javaex.db.condition.IConditionExpression;
import com.sasd13.proadmin.dao.RunningDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningConditionExpression implements IConditionExpression {

	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_YEAR + " = " + Integer.parseInt(value);
			} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_TEACHER_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_PROJECT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionBuilderException("Running key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionBuilderException("Running key '" + key + "' parameter parsing error");
		}
	}
}

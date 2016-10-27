package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return IRunningDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
				return IRunningDAO.COLUMN_YEAR + " = " + Integer.parseInt(value);
			} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
				return IRunningDAO.COLUMN_TEACHER_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
				return IRunningDAO.COLUMN_PROJECT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("Running key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("Running key '" + key + "' parameter parsing error");
		}
	}
}

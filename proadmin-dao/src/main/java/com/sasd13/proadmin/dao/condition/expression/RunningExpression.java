package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.RunningDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class RunningExpression implements IExpression {
	
	public static final RunningExpression INSTANCE = new RunningExpression();
	
	private RunningExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.YEAR.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_YEAR + " = " + Integer.parseInt(value);
			} else if (Parameter.TEACHER.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_TEACHER_ID + " = " + Long.parseLong(value);
			} else if (Parameter.PROJECT.getName().equalsIgnoreCase(key)) {
				return RunningDAO.COLUMN_PROJECT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("Running key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("Running key '" + key + "' parameter parsing error");
		}
	}
}

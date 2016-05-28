package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.StudentDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class StudentExpression implements IExpression {
	
	public static final StudentExpression INSTANCE = new StudentExpression();
	
	private StudentExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		if (Parameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return StudentDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Student key '" + key + "' parameter parsing error");
			}
		} else if (Parameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (Parameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (Parameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (Parameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (Parameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new ConditionException("Student key '" + key + "' is not a declared parameter");
		}
	}
}

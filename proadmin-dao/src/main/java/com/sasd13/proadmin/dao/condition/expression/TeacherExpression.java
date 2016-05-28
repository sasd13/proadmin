package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class TeacherExpression implements IExpression {
	
	public static final TeacherExpression INSTANCE = new TeacherExpression();
	
	private TeacherExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		if (Parameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return TeacherDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Teacher key '" + key + "' parameter parsing error");
			}
		} else if (Parameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return TeacherDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (Parameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return TeacherDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (Parameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return TeacherDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (Parameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return TeacherDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new ConditionException("Teacher key '" + key + "' is not a declared parameter");
		}
	}
}

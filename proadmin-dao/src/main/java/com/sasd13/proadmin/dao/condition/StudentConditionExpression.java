package com.sasd13.proadmin.dao.condition;

import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.javaex.db.condition.IConditionExpression;
import com.sasd13.proadmin.dao.StudentDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentConditionExpression implements IConditionExpression {
	
	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return StudentDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionBuilderException("Student key '" + key + "' parameter parsing error");
			}
		} else if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (EnumParameter.ACADEMICLEVEL.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_ACADEMICLEVEL + " = '" + value + "'";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return StudentDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new ConditionBuilderException("Student key '" + key + "' is not a declared parameter");
		}
	}
}

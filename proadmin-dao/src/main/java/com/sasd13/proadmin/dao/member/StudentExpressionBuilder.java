package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class StudentExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return IStudentDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Student key '" + key + "' parsing error");
			}
		} else if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return IStudentDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return IStudentDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return IStudentDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return IStudentDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new ConditionException("Student key '" + key + "' is not a declared parameter");
		}
	}
}

package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class TeacherExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return ITeacherDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("Teacher key '" + key + "' parameter parsing error");
			}
		} else if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_NUMBER + " = '" + value + "'";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new ConditionException("Teacher key '" + key + "' is not a declared parameter");
		}
	}
}

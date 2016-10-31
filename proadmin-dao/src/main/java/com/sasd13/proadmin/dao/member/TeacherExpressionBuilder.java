package com.sasd13.proadmin.dao.member;

import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class TeacherExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_CODE + " = '" + value + "'";
		} else if (EnumParameter.FIRSTNAME.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_FIRSTNAME + " = '" + value + "'";
		} else if (EnumParameter.LASTNAME.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_LASTNAME + " = '" + value + "'";
		} else if (EnumParameter.EMAIL.getName().equalsIgnoreCase(key)) {
			return ITeacherDAO.COLUMN_EMAIL + " = '" + value + "'";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}

package com.sasd13.proadmin.dao;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class AcademicLevelExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return IAcademicLevelDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("AcademicLevel key '" + key + "' parsing error");
			}
		} else if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IAcademicLevelDAO.COLUMN_CODE + " = '" + value + "'";
		} else {
			throw new ConditionException("AcademicLevel key '" + key + "' is not a declared parameter");
		}
	}
}

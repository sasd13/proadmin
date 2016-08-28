package com.sasd13.proadmin.dao.condition;

import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.javaex.db.condition.IConditionExpression;
import com.sasd13.proadmin.dao.ReportDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportConditionExpression implements IConditionExpression {

	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.SESSIONNUMBER.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_SESSIONNUMBER + " = " + Integer.parseInt(value);
			} else if (EnumParameter.RUNNINGTEAM.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_RUNNINGTEAM + " = " + Long.parseLong(value);
			} else {
				throw new ConditionBuilderException("Report key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionBuilderException("Report key '" + key + "' parameter parsing error");
		}
	}
}

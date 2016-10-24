package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.condition.ConditionBuilderException;
import com.sasd13.javaex.dao.condition.IConditionExpression;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportConditionExpression implements IConditionExpression {

	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return IReportDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.SESSIONNUMBER.getName().equalsIgnoreCase(key)) {
				return IReportDAO.COLUMN_SESSIONNUMBER + " = " + Integer.parseInt(value);
			} else if (EnumParameter.RUNNINGTEAM.getName().equalsIgnoreCase(key)) {
				return IReportDAO.COLUMN_RUNNINGTEAM + " = " + Long.parseLong(value);
			} else {
				throw new ConditionBuilderException("Report key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionBuilderException("Report key '" + key + "' parameter parsing error");
		}
	}
}

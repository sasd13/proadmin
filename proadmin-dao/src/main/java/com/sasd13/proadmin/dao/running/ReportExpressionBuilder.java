package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return IReportDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
				return IReportDAO.COLUMN_NUMBER + " = " + value;
			} else if (EnumParameter.SESSION.getName().equalsIgnoreCase(key)) {
				return IReportDAO.COLUMN_SESSION + " = " + Integer.parseInt(value);
			} else if (EnumParameter.RUNNINGTEAM.getName().equalsIgnoreCase(key)) {
				return IReportDAO.COLUMN_RUNNINGTEAM + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("Report key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("Report key '" + key + "' parameter parsing error");
		}
	}
}

package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.ReportDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class ReportExpression implements IExpression {
	
	public static final ReportExpression INSTANCE = new ReportExpression();
	
	private ReportExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.SESSIONNUMBER.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_SESSIONNUMBER + " = " + Integer.parseInt(value);
			} else if (Parameter.RUNNINGTEAM.getName().equalsIgnoreCase(key)) {
				return ReportDAO.COLUMN_RUNNINGTEAM + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("Report key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("Report key '" + key + "' parameter parsing error");
		}
	}
}

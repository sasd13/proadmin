package com.sasd13.proadmin.dao.condition;

import com.sasd13.javaex.db.condition.ConditionBuilderException;
import com.sasd13.javaex.db.condition.IConditionExpression;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class LeadEvaluationConditionExpression implements IConditionExpression {
	
	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_REPORT_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionBuilderException("LeadEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionBuilderException("LeadEvaluation key '" + key + "' parameter parsing error");
		}
	}
}

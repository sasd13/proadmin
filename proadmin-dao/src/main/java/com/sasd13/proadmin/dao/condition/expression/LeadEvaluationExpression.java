package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class LeadEvaluationExpression implements IExpression {
	
	public static final LeadEvaluationExpression INSTANCE = new LeadEvaluationExpression();
	
	private LeadEvaluationExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.REPORT.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_REPORT_ID + " = " + Long.parseLong(value);
			} else if (Parameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return LeadEvaluationDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("LeadEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("LeadEvaluation key '" + key + "' parameter parsing error");
		}
	}
}

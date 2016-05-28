package com.sasd13.proadmin.dao.condition.expression;

import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.condition.ConditionException;
import com.sasd13.proadmin.util.Parameter;

public class IndividualEvaluationExpression implements IExpression {
	
	public static final IndividualEvaluationExpression INSTANCE = new IndividualEvaluationExpression();
	
	private IndividualEvaluationExpression() {}
	
	@Override
	public String build(String key, String value) throws ConditionException {
		try {
			if (Parameter.ID.getName().equalsIgnoreCase(key)) {
				return IndividualEvaluationDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (Parameter.REPORT.getName().equalsIgnoreCase(key)) {
				return IndividualEvaluationDAO.COLUMN_REPORT_ID + " = " + Long.parseLong(value);
			} else if (Parameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return IndividualEvaluationDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionException("IndividualEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionException("IndividualEvaluation key '" + key + "' parameter parsing error");
		}
	}
}

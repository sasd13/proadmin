package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.condition.ConditionBuilderException;
import com.sasd13.javaex.dao.condition.IConditionExpression;
import com.sasd13.proadmin.util.EnumParameter;

public class IndividualEvaluationConditionExpression implements IConditionExpression {

	@Override
	public String build(String key, String value) throws ConditionBuilderException {
		try {
			if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
				return IIndividualEvaluationDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
				return IIndividualEvaluationDAO.COLUMN_REPORT_ID + " = " + Long.parseLong(value);
			} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
				return IIndividualEvaluationDAO.COLUMN_STUDENT_ID + " = " + Long.parseLong(value);
			} else {
				throw new ConditionBuilderException("IndividualEvaluation key '" + key + "' is not a declared parameter");
			}
		} catch (NumberFormatException e) {
			throw new ConditionBuilderException("IndividualEvaluation key '" + key + "' parameter parsing error");
		}
	}
}

package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.condition.ConditionException;
import com.sasd13.javaex.dao.condition.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class IndividualEvaluationExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.ID.getName().equalsIgnoreCase(key)) {
			try {
				return IIndividualEvaluationDAO.COLUMN_ID + " = " + Long.parseLong(value);
			} catch (NumberFormatException e) {
				throw new ConditionException("IndividualEvaluation key '" + key + "' parsing error");
			}
		} else if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_REPORT_CODE + " = " + value;
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_STUDENT_CODE + " = " + value;
		} else {
			throw new ConditionException("IndividualEvaluation key '" + key + "' is not a declared parameter");
		}
	}
}

package com.sasd13.proadmin.dao.running;

import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.proadmin.util.EnumParameter;

public class IndividualEvaluationExpressionBuilder implements IExpressionBuilder {

	@Override
	public String build(String key, String value) throws ConditionException {
		if (EnumParameter.REPORT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_REPORT_CODE + " = " + value;
		} else if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IIndividualEvaluationDAO.COLUMN_STUDENT_CODE + " = " + value;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}

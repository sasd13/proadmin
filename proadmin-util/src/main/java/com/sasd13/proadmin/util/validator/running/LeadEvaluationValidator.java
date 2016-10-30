package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public class LeadEvaluationValidator implements IValidator<LeadEvaluation> {

	@Override
	public void validate(LeadEvaluation leadEvaluation) throws ValidatorException {
		if (leadEvaluation == null) {
			throw new ValidatorException("IndividualEvaluation: null");
		}

		if (leadEvaluation.getReport() == null || StringUtils.isBlank(leadEvaluation.getReport().getNumber())) {
			throw new ValidatorException("IndividualEvaluation: report is not valid");
		}

		if (leadEvaluation.getStudent() == null || StringUtils.isBlank(leadEvaluation.getStudent().getNumber())) {
			throw new ValidatorException("IndividualEvaluation: student is not valid");
		}
	}
}
package com.sasd13.proadmin.util.dao.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.validator.IValidator;
import com.sasd13.javaex.dao.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public class IndividualEvaluationValidator implements IValidator<IndividualEvaluation> {

	@Override
	public void validate(IndividualEvaluation individualEvaluation) throws ValidatorException {
		if (individualEvaluation == null) {
			throw new ValidatorException("IndividualEvaluation: null");
		}

		if (individualEvaluation.getReport() == null || StringUtils.isBlank(individualEvaluation.getReport().getNumber())) {
			throw new ValidatorException("IndividualEvaluation: report is not valid");
		}

		if (individualEvaluation.getStudent() == null || StringUtils.isBlank(individualEvaluation.getStudent().getNumber())) {
			throw new ValidatorException("IndividualEvaluation: student is not valid");
		}
	}
}

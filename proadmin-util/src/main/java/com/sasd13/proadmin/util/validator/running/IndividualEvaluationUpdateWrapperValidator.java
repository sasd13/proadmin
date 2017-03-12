package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;

public class IndividualEvaluationUpdateWrapperValidator implements IValidator<IUpdateWrapper<IndividualEvaluation>> {

	private IndividualEvaluationValidator individualEvaluationValidator;

	public IndividualEvaluationUpdateWrapperValidator() {
		individualEvaluationValidator = new IndividualEvaluationValidator();
	}

	@Override
	public void validate(IUpdateWrapper<IndividualEvaluation> updateWrapper) throws ValidatorException {
		IndividualEvaluationUpdateWrapper individualEvaluationUpdateWrapper = (IndividualEvaluationUpdateWrapper) updateWrapper;

		if (individualEvaluationUpdateWrapper == null) {
			throw new ValidatorException("IndividualEvaluationUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(individualEvaluationUpdateWrapper.getReportNumber())) {
			throw new ValidatorException("IndividualEvaluationUpdateWrapper : report -> number is not valid");
		}

		if (StringUtils.isBlank(individualEvaluationUpdateWrapper.getStudentNumber())) {
			throw new ValidatorException("IndividualEvaluationUpdateWrapper : student -> number is not valid");
		}

		individualEvaluationValidator.validate(individualEvaluationUpdateWrapper.getWrapped());
	}
}

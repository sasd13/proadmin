package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;

public class LeadEvaluationUpdateWrapperValidator implements IValidator<IUpdateWrapper<LeadEvaluation>> {

	private LeadEvaluationValidator leadEvaluationValidator;

	public LeadEvaluationUpdateWrapperValidator() {
		leadEvaluationValidator = new LeadEvaluationValidator();
	}

	@Override
	public void validate(IUpdateWrapper<LeadEvaluation> updateWrapper) throws ValidatorException {
		LeadEvaluationUpdateWrapper leadEvaluationUpdateWrapper = (LeadEvaluationUpdateWrapper) updateWrapper;

		if (leadEvaluationUpdateWrapper == null) {
			throw new ValidatorException("LeadEvaluationUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(leadEvaluationUpdateWrapper.getReportNumber())) {
			throw new ValidatorException("LeadEvaluationUpdateWrapper : report -> number is not valid");
		}

		leadEvaluationValidator.validate(leadEvaluationUpdateWrapper.getWrapped());
	}
}

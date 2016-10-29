package com.sasd13.proadmin.util.dao.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.validator.IValidator;
import com.sasd13.javaex.dao.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.Running;

public class RunningValidator implements IValidator<Running> {

	@Override
	public void validate(Running running) throws ValidatorException {
		if (running == null) {
			throw new ValidatorException("Running: null");
		}

		if (running.getProject() == null || StringUtils.isBlank(running.getProject().getCode())) {
			throw new ValidatorException("IndividualEvaluation: project is not valid");
		}

		if (running.getTeacher() == null || StringUtils.isBlank(running.getTeacher().getNumber())) {
			throw new ValidatorException("IndividualEvaluation: teacher is not valid");
		}
	}
}

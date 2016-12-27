package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.Running;

public class RunningValidator implements IValidator<Running> {

	@Override
	public void validate(Running running) throws ValidatorException {
		if (running == null) {
			throw new ValidatorException("Running is not valid");
		}
		
		if (running.getYear() < 0) {
			throw new ValidatorException("Running : year is not valid");
		}

		if (running.getProject() == null || StringUtils.isBlank(running.getProject().getCode())) {
			throw new ValidatorException("Running : project -> code is not valid");
		}

		if (running.getTeacher() == null || StringUtils.isBlank(running.getTeacher().getNumber())) {
			throw new ValidatorException("Running : teacher -> number is not valid");
		}
	}
}

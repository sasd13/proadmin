package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;

public class RunningUpdateWrapperValidator implements IValidator<IUpdateWrapper<Running>> {

	private RunningValidator runningValidator;

	public RunningUpdateWrapperValidator() {
		runningValidator = new RunningValidator();
	}

	@Override
	public void validate(IUpdateWrapper<Running> updateWrapper) throws ValidatorException {
		RunningUpdateWrapper runningUpdateWrapper = (RunningUpdateWrapper) updateWrapper;

		if (runningUpdateWrapper == null) {
			throw new ValidatorException("RunningUpdateWrapper is not valid");
		}

		if (runningUpdateWrapper.getYear() < 0) {
			throw new ValidatorException("RunningUpdateWrapper : year is not valid");
		}

		if (StringUtils.isBlank(runningUpdateWrapper.getProjectCode())) {
			throw new ValidatorException("RunningUpdateWrapper : project -> code is not valid");
		}

		if (StringUtils.isBlank(runningUpdateWrapper.getTeacherNumber())) {
			throw new ValidatorException("RunningUpdateWrapper : teacher -> number is not valid");
		}

		runningValidator.validate(runningUpdateWrapper.getWrapped());
	}
}

package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;

public class RunningUpdateWrapperValidator implements IValidator<IUpdateWrapper<Running>> {

	private RunningValidator runningValidator;

	public RunningUpdateWrapperValidator() {
		runningValidator = new RunningValidator();
	}

	@Override
	public void validate(IUpdateWrapper<Running> updateWrapper) throws ValidatorException {
		IRunningUpdateWrapper runningUpdateWrapper = (IRunningUpdateWrapper) updateWrapper;

		if (runningUpdateWrapper == null) {
			throw new ValidatorException("RunningUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(runningUpdateWrapper.getProjectCode())) {
			throw new ValidatorException("RunningUpdateWrapper : project code is not valid");
		}

		if (StringUtils.isBlank(runningUpdateWrapper.getTeacherNumber())) {
			throw new ValidatorException("RunningUpdateWrapper : teacher number is not valid");
		}

		runningValidator.validate(runningUpdateWrapper.getWrapped());
	}
}

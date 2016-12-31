package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningTeamUpdateWrapper;

public class RunningTeamUpdateWrapperValidator implements IValidator<IUpdateWrapper<RunningTeam>> {

	private RunningTeamValidator runningTeamValidator;

	public RunningTeamUpdateWrapperValidator() {
		runningTeamValidator = new RunningTeamValidator();
	}

	@Override
	public void validate(IUpdateWrapper<RunningTeam> updateWrapper) throws ValidatorException {
		IRunningTeamUpdateWrapper runningTeamUpdateWrapper = (IRunningTeamUpdateWrapper) updateWrapper;

		if (runningTeamUpdateWrapper == null) {
			throw new ValidatorException("RunningTeamUpdateWrapper is not valid");
		}

		if (StringUtils.isBlank(runningTeamUpdateWrapper.getTeacherNumber())) {
			throw new ValidatorException("RunningTeamUpdateWrapper : team -> number is not valid");
		}
		
		if (StringUtils.isBlank(runningTeamUpdateWrapper.getAcademicLevelCode())) {
			throw new ValidatorException("RunningTeamUpdateWrapper : academicLevel -> code is not valid");
		}
		
		if (runningTeamUpdateWrapper.getRunningYear() < 0) {
			throw new ValidatorException("RunningTeamUpdateWrapper : running -> year is not valid");
		}

		if (StringUtils.isBlank(runningTeamUpdateWrapper.getProjectCode())) {
			throw new ValidatorException("RunningTeamUpdateWrapper : running -> project -> code is not valid");
		}

		if (StringUtils.isBlank(runningTeamUpdateWrapper.getTeacherNumber())) {
			throw new ValidatorException("RunningTeamUpdateWrapper : running -> teacher -> number is not valid");
		}

		runningTeamValidator.validate(runningTeamUpdateWrapper.getWrapped());
	}
}

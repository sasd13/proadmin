package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class RunningTeamValidator implements IValidator<RunningTeam> {

	@Override
	public void validate(RunningTeam runningTeam) throws ValidatorException {
		if (runningTeam == null) {
			throw new ValidatorException("RunningTeam: null");
		}

		if (runningTeam.getAcademicLevel() == null || StringUtils.isBlank(runningTeam.getAcademicLevel().getCode())) {
			throw new ValidatorException("RunningTeam: academicLevel is not valid");
		}

		Running running = runningTeam.getRunning();

		if (running == null) {
			throw new ValidatorException("RunningTeam: running is not valid");
		}

		if (running.getProject() == null || StringUtils.isBlank(running.getProject().getCode())) {
			throw new ValidatorException("RunningTeam: running -> project is not valid");
		}

		if (running.getTeacher() == null || StringUtils.isBlank(running.getTeacher().getNumber())) {
			throw new ValidatorException("RunningTeam: running -> teacher is not valid");
		}
	}
}

package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.validator.IValidator;
import com.sasd13.javaex.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class ReportValidator implements IValidator<Report> {

	@Override
	public void validate(Report report) throws ValidatorException {
		if (report == null) {
			throw new ValidatorException("Report: null");
		}

		if (StringUtils.isBlank(report.getNumber())) {
			throw new ValidatorException("Report: number is not valid");
		}

		RunningTeam runningTeam = report.getRunningTeam();

		if (runningTeam == null) {
			throw new ValidatorException("Report: runningTeam is not valid");
		}

		if (runningTeam.getAcademicLevel() == null || StringUtils.isBlank(runningTeam.getAcademicLevel().getCode())) {
			throw new ValidatorException("Report: runningTeam -> academicLevel is not valid");
		}

		Running running = runningTeam.getRunning();

		if (running == null) {
			throw new ValidatorException("Report: runningTeam -> running is not valid");
		}

		if (running.getProject() == null || StringUtils.isBlank(running.getProject().getCode())) {
			throw new ValidatorException("Report: runningTeam -> running -> project is not valid");
		}

		if (running.getTeacher() == null || StringUtils.isBlank(running.getTeacher().getNumber())) {
			throw new ValidatorException("Report: runningTeam -> running -> teacher is not valid");
		}

		if (report.getMeetingDate() == null) {
			throw new ValidatorException("Report: meetingDate is not valid");
		}
	}
}

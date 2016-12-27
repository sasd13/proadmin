package com.sasd13.proadmin.util.validator.running;

import org.apache.commons.lang3.StringUtils;

import com.sasd13.javaex.util.validator.IValidator;
import com.sasd13.javaex.util.validator.ValidatorException;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class ReportValidator implements IValidator<Report> {

	@Override
	public void validate(Report report) throws ValidatorException {
		if (report == null) {
			throw new ValidatorException("Report is not valid");
		}

		if (StringUtils.isBlank(report.getNumber())) {
			throw new ValidatorException("Report : number is not valid");
		}

		if (report.getDateMeeting() == null) {
			throw new ValidatorException("Report : dateMeeting is not valid");
		}

		RunningTeam runningTeam = report.getRunningTeam();

		if (runningTeam == null) {
			throw new ValidatorException("Report : runningTeam is not valid");
		}

		if (runningTeam.getTeam() == null || StringUtils.isBlank(runningTeam.getTeam().getNumber())) {
			throw new ValidatorException("Report : runningTeam -> team -> number is not valid");
		}

		if (runningTeam.getAcademicLevel() == null || StringUtils.isBlank(runningTeam.getAcademicLevel().getCode())) {
			throw new ValidatorException("Report : runningTeam -> academicLevel -> code is not valid");
		}

		Running running = runningTeam.getRunning();

		if (running == null) {
			throw new ValidatorException("Report : runningTeam -> running is not valid");
		}
		
		if (running.getYear() < 0) {
			throw new ValidatorException("Report : runningTeam -> running -> year is not valid");
		}

		if (running.getProject() == null || StringUtils.isBlank(running.getProject().getCode())) {
			throw new ValidatorException("Report : runningTeam -> running -> project -> code is not valid");
		}

		if (running.getTeacher() == null || StringUtils.isBlank(running.getTeacher().getNumber())) {
			throw new ValidatorException("Report : runningTeam -> running -> teacher -> number is not valid");
		}
	}
}

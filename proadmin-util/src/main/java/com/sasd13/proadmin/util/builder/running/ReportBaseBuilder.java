package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.Report;

public class ReportBaseBuilder implements IBuilder<Report> {

	private String number, projectCode, teacherNumber, teamNumber, academicLevelCode;
	private int year;

	public ReportBaseBuilder(int year, String projectCode, String teacherNumber, String teamNumber, String academicLevelCode) {
		this(null, year, projectCode, teacherNumber, teamNumber, academicLevelCode);
	}

	public ReportBaseBuilder(String number, int year, String projectCode, String teacherNumber, String teamNumber, String academicLevelCode) {
		this.number = number;
		this.year = year;
		this.projectCode = projectCode;
		this.teacherNumber = teacherNumber;
		this.teamNumber = teamNumber;
		this.academicLevelCode = academicLevelCode;
	}

	@Override
	public Report build() {
		Report report = new Report();
		report.setNumber(number);
		report.setRunningTeam(new RunningTeamBaseBuilder(year, projectCode, teacherNumber, teamNumber, academicLevelCode).build());

		return report;
	}
}

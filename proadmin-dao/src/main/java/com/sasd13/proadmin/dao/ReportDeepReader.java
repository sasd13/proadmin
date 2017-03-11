package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;

public class ReportDeepReader extends DeepReader<Report> {

	private RunningTeamDeepReader runningTeamDeepReader;
	private Map<String, String[]> parameters;

	public ReportDeepReader(IReportDAO reportDAO, RunningTeamDeepReader runningTeamDeepReader) {
		super(reportDAO);

		this.runningTeamDeepReader = runningTeamDeepReader;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieveData(Report report) {
		retrieveDataRunningTeam(report);
	}

	private void retrieveDataRunningTeam(Report report) {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(report.getRunningTeam().getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { report.getRunningTeam().getRunning().getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { report.getRunningTeam().getRunning().getTeacher().getNumber() });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { report.getRunningTeam().getTeam().getNumber() });
		parameters.put(EnumParameter.ACADEMICLEVEL.getName(), new String[] { report.getRunningTeam().getAcademicLevel().getCode() });

		RunningTeam runningTeam = runningTeamDeepReader.select(parameters).get(0);
		report.setRunningTeam(runningTeam);
	}
}
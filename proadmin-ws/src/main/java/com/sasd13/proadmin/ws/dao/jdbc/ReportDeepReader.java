package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.running.IReport;
import com.sasd13.proadmin.bean.running.IRunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.IReportDAO;

public class ReportDeepReader extends DeepReader<IReport> {

	private RunningTeamDeepReader runningTeamDeepReader;
	private Map<String, String[]> parameters;

	public ReportDeepReader(IReportDAO reportDAO, RunningTeamDeepReader runningTeamDeepReader) {
		super(reportDAO);

		this.runningTeamDeepReader = runningTeamDeepReader;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieve(IReport iReport) {
		retrieveDataRunningTeam(iReport);
	}

	private void retrieveDataRunningTeam(IReport iReport) {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(iReport.getRunningTeam().getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { iReport.getRunningTeam().getRunning().getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { iReport.getRunningTeam().getRunning().getTeacher().getIntermediary() });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { iReport.getRunningTeam().getTeam().getNumber() });
		parameters.put(EnumParameter.ACADEMICLEVEL.getName(), new String[] { iReport.getRunningTeam().getAcademicLevel().getCode() });

		IRunningTeam iRunningTeam = runningTeamDeepReader.read(parameters).get(0);
		iReport.setRunningTeam(iRunningTeam);
	}
}
package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.RunningTeam;
import com.sasd13.proadmin.ws.dao.IReportDAO;

public class ReportDeepReader extends DeepReader<Report> {

	private RunningTeamDeepReader runningTeamDeepReader;
	private Map<String, String[]> criterias;

	public ReportDeepReader(IReportDAO reportDAO, RunningTeamDeepReader runningTeamDeepReader) {
		super(reportDAO);

		this.runningTeamDeepReader = runningTeamDeepReader;
		criterias = new HashMap<>();
	}

	@Override
	protected void retrieve(Report report) {
		retrieveDataRunningTeam(report);
	}

	private void retrieveDataRunningTeam(Report report) {
		criterias.clear();
		criterias.put(EnumCriteria.YEAR.getCode(), new String[] { String.valueOf(report.getRunningTeam().getRunning().getYear()) });
		criterias.put(EnumCriteria.PROJECT.getCode(), new String[] { report.getRunningTeam().getRunning().getProject().getCode() });
		criterias.put(EnumCriteria.TEACHER.getCode(), new String[] { report.getRunningTeam().getRunning().getTeacher().getIntermediary() });
		criterias.put(EnumCriteria.TEAM.getCode(), new String[] { report.getRunningTeam().getTeam().getNumber() });
		criterias.put(EnumCriteria.ACADEMICLEVEL.getCode(), new String[] { report.getRunningTeam().getAcademicLevel().getCode() });

		RunningTeam runningTeam = runningTeamDeepReader.read(criterias).get(0);
		report.setRunningTeam(runningTeam);
	}
}
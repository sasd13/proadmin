package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamDeepReader extends DeepReader<RunningTeam> {

	private RunningDeepReader runningDeepReader;
	private ITeamDAO teamDAO;
	private IAcademicLevelDAO academicLevelDAO;
	private ReportDeepReader reportDeepReader;
	private Map<String, String[]> parameters;

	public RunningTeamDeepReader(IRunningTeamDAO runningTeamDAO, RunningDeepReader runningDeepReader, ITeamDAO teamDAO, IAcademicLevelDAO academicLevelDAO, ReportDeepReader reportDeepReader) {
		super(runningTeamDAO);

		this.runningDeepReader = runningDeepReader;
		this.teamDAO = teamDAO;
		this.academicLevelDAO = academicLevelDAO;
		this.reportDeepReader = reportDeepReader;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieveData(RunningTeam runningTeam) {
		retrieveDataRunning(runningTeam);
		retrieveDataTeam(runningTeam);
		retrieveDataAcademicLevel(runningTeam);
		retrieveDataReports(runningTeam);
	}

	private void retrieveDataRunning(RunningTeam runningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(runningTeam.getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { runningTeam.getRunning().getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { runningTeam.getRunning().getTeacher().getNumber() });

		Running running = runningDeepReader.select(parameters).get(0);
		runningTeam.setRunning(running);
	}

	private void retrieveDataTeam(RunningTeam runningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { runningTeam.getTeam().getNumber() });

		Team team = teamDAO.select(parameters).get(0);
		runningTeam.setTeam(team);
	}

	private void retrieveDataAcademicLevel(RunningTeam runningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { runningTeam.getAcademicLevel().getCode() });

		AcademicLevel academicLevel = academicLevelDAO.select(parameters).get(0);
		runningTeam.setAcademicLevel(academicLevel);
	}

	private void retrieveDataReports(RunningTeam runningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(runningTeam.getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { runningTeam.getRunning().getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { runningTeam.getRunning().getTeacher().getNumber() });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { runningTeam.getTeam().getNumber() });
		parameters.put(EnumParameter.ACADEMICLEVEL.getName(), new String[] { runningTeam.getAcademicLevel().getCode() });

		List<Report> reports = reportDeepReader.select(parameters);
		runningTeam.getReports().clear();

		for (Report report : reports) {
			runningTeam.getReports().add(report);
			report.setRunningTeam(runningTeam);
		}
	}
}
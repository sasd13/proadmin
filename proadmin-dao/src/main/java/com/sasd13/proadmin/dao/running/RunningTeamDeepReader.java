package com.sasd13.proadmin.dao.running;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.member.ITeamDAO;
import com.sasd13.proadmin.util.Binder;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamDeepReader extends DeepReader<RunningTeam> {

	private RunningDeepReader runningDeepReader;
	private ITeamDAO teamDAO;
	private ReportDeepReader reportDeepReader;

	public RunningTeamDeepReader(IRunningTeamDAO runningTeamDAO, RunningDeepReader runningDeepReader, ITeamDAO teamDAO, ReportDeepReader reportDeepReader) {
		super(runningTeamDAO);

		this.runningDeepReader = runningDeepReader;
		this.teamDAO = teamDAO;
		this.reportDeepReader = reportDeepReader;
	}

	@Override
	protected void retrieveData(RunningTeam runningTeam) throws DAOException {
		Map<String, String[]> parameters = new HashMap<String, String[]>();

		retrieveDataRunning(runningTeam, parameters);
		retrieveDataTeam(runningTeam, parameters);
		retrieveDataReports(runningTeam, parameters);
	}

	private void retrieveDataRunning(RunningTeam runningTeam, Map<String, String[]> parameters) throws DAOException {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(runningTeam.getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { String.valueOf(runningTeam.getRunning().getProject().getCode()) });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { String.valueOf(runningTeam.getRunning().getTeacher().getNumber()) });

		Running running = runningDeepReader.select(parameters).get(0);

		Binder.bind(runningTeam.getRunning(), running);
	}

	private void retrieveDataTeam(RunningTeam runningTeam, Map<String, String[]> parameters) throws DAOException {
		parameters.clear();
		parameters.put(EnumParameter.TEAM.getName(), new String[] { String.valueOf(runningTeam.getTeam().getNumber()) });

		Team team = teamDAO.select(parameters).get(0);

		Binder.bind(runningTeam.getTeam(), team);
	}

	private void retrieveDataReports(RunningTeam runningTeam, Map<String, String[]> parameters) throws DAOException {
		runningTeam.getReports().clear();
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(runningTeam.getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { String.valueOf(runningTeam.getRunning().getProject().getCode()) });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { String.valueOf(runningTeam.getRunning().getTeacher().getNumber()) });
		parameters.put(EnumParameter.TEAM.getName(), new String[] { String.valueOf(runningTeam.getTeam().getNumber()) });
		parameters.put(EnumParameter.ACADEMICLEVEL.getName(), new String[] { String.valueOf(runningTeam.getAcademicLevel().getCode()) });

		List<Report> reports = reportDeepReader.select(parameters);

		Report reportToAdd;
		for (Report report : reports) {
			reportToAdd = new Report();

			runningTeam.getReports().add(report);
			Binder.bind(reportToAdd, report);
			// TODO : dependency binder
		}
	}
}

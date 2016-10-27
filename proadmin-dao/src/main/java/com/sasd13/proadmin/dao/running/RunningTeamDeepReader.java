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

	private IRunningDAO runningDAO;
	private ITeamDAO teamDAO;
	private ReportDeepReader reportDeepReader;

	public RunningTeamDeepReader(IRunningTeamDAO runningTeamDAO, IRunningDAO runningDAO, ITeamDAO teamDAO, ReportDeepReader reportDeepReader) {
		super(runningTeamDAO);

		this.runningDAO = runningDAO;
		this.teamDAO = teamDAO;
		this.reportDeepReader = reportDeepReader;
	}

	@Override
	protected void retrieveData(RunningTeam runningTeam) throws DAOException {
		Running running = runningDAO.select(runningTeam.getRunning().getId());
		Binder.bind(runningTeam.getRunning(), running);

		Team team = teamDAO.select(runningTeam.getTeam().getId());
		Binder.bind(runningTeam.getTeam(), team);

		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put(EnumParameter.RUNNINGTEAM.getName(), new String[] { String.valueOf(runningTeam.getId()) });

		runningTeam.getReports().clear();

		List<Report> reports = reportDeepReader.select(parameters);
		Report reportToAdd;
		for (Report report : reports) {
			reportToAdd = new Report(runningTeam);
			Binder.bind(reportToAdd, report);
		}
	}
}

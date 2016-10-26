package com.sasd13.proadmin.dao.running;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.member.ITeamDAO;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamDeepReader extends DeepReader<RunningTeam> {

	private IRunningDAO runningDAO;
	private ITeamDAO teamDAO;
	private IReportDAO reportDAO;

	public RunningTeamDeepReader(IEntityDAO<RunningTeam> entityDAO, IRunningDAO runningDAO, ITeamDAO teamDAO, IReportDAO reportDAO) {
		super(entityDAO);

		this.runningDAO = runningDAO;
		this.teamDAO = teamDAO;
		this.reportDAO = reportDAO;
	}

	@Override
	protected void retrieveData(RunningTeam runningTeam) throws DAOException {
		Running running = runningDAO.select(runningTeam.getRunning().getId());
		runningTeam.getRunning().setYear(running.getYear());
		runningTeam.getRunning().setTeacher(running.getTeacher());
		runningTeam.getRunning().setProject(running.getProject());

		Team team = teamDAO.select(runningTeam.getTeam().getId());
		runningTeam.getTeam().setCode(team.getCode());

		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put(EnumParameter.RUNNINGTEAM.getName(), new String[] { String.valueOf(runningTeam.getId()) });

		List<Report> reports = reportDAO.select(parameters);
		Report reportToAdd;
		for (Report report : reports) {
			reportToAdd = new Report(runningTeam);
			reportToAdd.setId(report.getId());
			reportToAdd.setNumber(report.getNumber());
			reportToAdd.setMeetingDate(report.getMeetingDate());
			reportToAdd.setSession(report.getSession());
			reportToAdd.setComment(report.getComment());
		}
	}
}

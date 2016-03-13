package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.db.DeepReader;
import com.sasd13.javaex.db.IEntityDAO;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.Parameter;

public class RunningTeamDeepReader extends DeepReader<RunningTeam> {
	
	private RunningDAO runningDAO;
	private TeamDAO teamDAO;
	private ReportDAO reportDAO;
	
	public RunningTeamDeepReader(IEntityDAO<RunningTeam> entityDAO, RunningDAO runningDAO, TeamDAO teamDAO, ReportDAO reportDAO) {
		super(entityDAO);
		
		this.runningDAO = runningDAO;
		this.teamDAO = teamDAO;
		this.reportDAO = reportDAO;
	}
	
	@Override
	protected void retrieveData(RunningTeam runningTeam) {
		Running running = runningDAO.select(runningTeam.getRunning().getId());
		runningTeam.getRunning().setYear(running.getYear());
		runningTeam.getRunning().setTeacher(running.getTeacher());
		runningTeam.getRunning().setProject(running.getProject());
		
		Team team = teamDAO.select(runningTeam.getTeam().getId());
		runningTeam.getTeam().setCode(team.getCode());
		
		Map<String, String[]> parameters = new HashMap<String, String[]>();
		parameters.put(Parameter.RUNNINGTEAM.getName(), new String[]{ String.valueOf(runningTeam.getId()) });
		
		List<Report> reports = reportDAO.select(parameters);
		Report reportToAdd;
		for (Report report : reports) {
			reportToAdd = new Report(runningTeam);
			reportToAdd.setId(report.getId());
			reportToAdd.setMeetingDate(report.getMeetingDate());
			reportToAdd.setWeek(report.getWeek());
			reportToAdd.setComment(report.getComment());
		}
	}
}

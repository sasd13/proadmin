package com.sasd13.proadmin.dao;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.EnumParameter;

public class RunningTeamDeepReader extends DeepReader<RunningTeam> {

	private RunningDeepReader runningDeepReader;
	private ITeamDAO teamDAO;
	private IAcademicLevelDAO academicLevelDAO;
	private Map<String, String[]> parameters;

	public RunningTeamDeepReader(IRunningTeamDAO runningTeamDAO, RunningDeepReader runningDeepReader, ITeamDAO teamDAO, IAcademicLevelDAO academicLevelDAO) {
		super(runningTeamDAO);

		this.runningDeepReader = runningDeepReader;
		this.teamDAO = teamDAO;
		this.academicLevelDAO = academicLevelDAO;
		parameters = new HashMap<>();
	}

	@Override
	protected void retrieve(RunningTeam runningTeam) {
		retrieveDataRunning(runningTeam);
		retrieveDataTeam(runningTeam);
		retrieveDataAcademicLevel(runningTeam);
	}

	private void retrieveDataRunning(RunningTeam runningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(runningTeam.getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { runningTeam.getRunning().getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { runningTeam.getRunning().getTeacher().getNumber() });

		Running running = runningDeepReader.read(parameters).get(0);
		runningTeam.setRunning(running);
	}

	private void retrieveDataTeam(RunningTeam runningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { runningTeam.getTeam().getNumber() });

		Team team = teamDAO.read(parameters).get(0);
		runningTeam.setTeam(team);
	}

	private void retrieveDataAcademicLevel(RunningTeam runningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { runningTeam.getAcademicLevel().getCode() });

		AcademicLevel academicLevel = academicLevelDAO.read(parameters).get(0);
		runningTeam.setAcademicLevel(academicLevel);
	}
}

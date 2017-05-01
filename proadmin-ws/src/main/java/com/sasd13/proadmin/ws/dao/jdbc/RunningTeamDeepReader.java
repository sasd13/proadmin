package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.AcademicLevel;
import com.sasd13.proadmin.ws.bean.Running;
import com.sasd13.proadmin.ws.bean.RunningTeam;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws.dao.ITeamDAO;

public class RunningTeamDeepReader extends DeepReader<RunningTeam> {

	private RunningDeepReader runningDeepReader;
	private ITeamDAO teamDAO;
	private IAcademicLevelDAO academicLevelDAO;
	private Map<String, String[]> criterias;

	public RunningTeamDeepReader(IRunningTeamDAO runningTeamDAO, RunningDeepReader runningDeepReader, ITeamDAO teamDAO, IAcademicLevelDAO academicLevelDAO) {
		super(runningTeamDAO);

		this.runningDeepReader = runningDeepReader;
		this.teamDAO = teamDAO;
		this.academicLevelDAO = academicLevelDAO;
		criterias = new HashMap<>();
	}

	@Override
	protected void retrieve(RunningTeam runningTeam) {
		retrieveDataRunning(runningTeam);
		retrieveDataTeam(runningTeam);
		retrieveDataAcademicLevel(runningTeam);
	}

	private void retrieveDataRunning(RunningTeam runningTeam) {
		criterias.clear();
		criterias.put(EnumCriteria.YEAR.getCode(), new String[] { String.valueOf(runningTeam.getRunning().getYear()) });
		criterias.put(EnumCriteria.PROJECT.getCode(), new String[] { runningTeam.getRunning().getProject().getCode() });
		criterias.put(EnumCriteria.TEACHER.getCode(), new String[] { runningTeam.getRunning().getTeacher().getIntermediary() });

		Running running = runningDeepReader.read(criterias).get(0);
		runningTeam.setRunning(running);
	}

	private void retrieveDataTeam(RunningTeam runningTeam) {
		criterias.clear();
		criterias.put(EnumCriteria.NUMBER.getCode(), new String[] { runningTeam.getTeam().getNumber() });

		Team team = teamDAO.read(criterias).get(0);
		runningTeam.setTeam(team);
	}

	private void retrieveDataAcademicLevel(RunningTeam runningTeam) {
		criterias.clear();
		criterias.put(EnumCriteria.CODE.getCode(), new String[] { runningTeam.getAcademicLevel().getCode() });

		AcademicLevel academicLevel = academicLevelDAO.read(criterias).get(0);
		runningTeam.setAcademicLevel(academicLevel);
	}
}

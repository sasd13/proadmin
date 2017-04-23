package com.sasd13.proadmin.ws.dao.jdbc;

import java.util.HashMap;
import java.util.Map;

import com.sasd13.javaex.dao.DeepReader;
import com.sasd13.proadmin.bean.level.IAcademicLevel;
import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.bean.running.IRunningTeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.IAcademicLevelDAO;
import com.sasd13.proadmin.ws.dao.IRunningTeamDAO;
import com.sasd13.proadmin.ws.dao.ITeamDAO;

public class RunningTeamDeepReader extends DeepReader<IRunningTeam> {

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
	protected void retrieve(IRunningTeam iRunningTeam) {
		retrieveDataRunning(iRunningTeam);
		retrieveDataTeam(iRunningTeam);
		retrieveDataAcademicLevel(iRunningTeam);
	}

	private void retrieveDataRunning(IRunningTeam iRunningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.YEAR.getName(), new String[] { String.valueOf(iRunningTeam.getRunning().getYear()) });
		parameters.put(EnumParameter.PROJECT.getName(), new String[] { iRunningTeam.getRunning().getProject().getCode() });
		parameters.put(EnumParameter.TEACHER.getName(), new String[] { iRunningTeam.getRunning().getTeacher().getIntermediary() });

		IRunning iRunning = runningDeepReader.read(parameters).get(0);
		iRunningTeam.setRunning(iRunning);
	}

	private void retrieveDataTeam(IRunningTeam iRunningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.NUMBER.getName(), new String[] { iRunningTeam.getTeam().getNumber() });

		ITeam iTeam = teamDAO.read(parameters).get(0);
		iRunningTeam.setTeam(iTeam);
	}

	private void retrieveDataAcademicLevel(IRunningTeam iRunningTeam) {
		parameters.clear();
		parameters.put(EnumParameter.CODE.getName(), new String[] { iRunningTeam.getAcademicLevel().getCode() });

		IAcademicLevel iAcademicLevel = academicLevelDAO.read(parameters).get(0);
		iRunningTeam.setAcademicLevel(iAcademicLevel);
	}
}

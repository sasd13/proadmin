package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.proadmin.bean.running.RunningTeam;

public class RunningTeamUpdateWrapper implements IRunningTeamUpdateWrapper {

	private RunningTeam runningTeam;
	private int runningYear;
	private String projectCode, teacherNumber, teamNumber, academicLevelCode;

	@Override
	public RunningTeam getWrapped() {
		return runningTeam;
	}

	@Override
	public void setWrapped(RunningTeam runningTeam) {
		this.runningTeam = runningTeam;
	}

	@Override
	public int getRunningYear() {
		return runningYear;
	}

	@Override
	public void setRunningYear(int runningYear) {
		this.runningYear = runningYear;
	}

	@Override
	public String getProjectCode() {
		return projectCode;
	}

	@Override
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	@Override
	public String getTeacherNumber() {
		return teacherNumber;
	}

	@Override
	public void setTeacherNumber(String teacherNumber) {
		this.teacherNumber = teacherNumber;
	}

	@Override
	public String getTeamNumber() {
		return teamNumber;
	}

	@Override
	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	@Override
	public String getAcademicLevelCode() {
		return academicLevelCode;
	}

	@Override
	public void setAcademicLevelCode(String academicLevelCode) {
		this.academicLevelCode = academicLevelCode;
	}
}

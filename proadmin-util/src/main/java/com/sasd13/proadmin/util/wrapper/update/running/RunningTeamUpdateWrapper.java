package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IRunningTeam;

public class RunningTeamUpdateWrapper implements IUpdateWrapper<IRunningTeam> {

	private IRunningTeam iRunningTeam;
	private int runningYear;
	private String projectCode, teacherIntermediary, teamNumber, academicLevelCode;

	@Override
	public IRunningTeam getWrapped() {
		return iRunningTeam;
	}

	@Override
	public void setWrapped(IRunningTeam iRunningTeam) {
		this.iRunningTeam = iRunningTeam;
	}

	public int getRunningYear() {
		return runningYear;
	}

	public void setRunningYear(int runningYear) {
		this.runningYear = runningYear;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getTeacherIntermediary() {
		return teacherIntermediary;
	}

	public void setTeacherIntermediary(String teacherIntermediary) {
		this.teacherIntermediary = teacherIntermediary;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getAcademicLevelCode() {
		return academicLevelCode;
	}

	public void setAcademicLevelCode(String academicLevelCode) {
		this.academicLevelCode = academicLevelCode;
	}
}

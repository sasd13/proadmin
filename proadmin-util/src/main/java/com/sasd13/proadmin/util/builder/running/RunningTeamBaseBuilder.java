package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.builder.AcademicLevelBaseBuilder;
import com.sasd13.proadmin.util.builder.member.TeamBaseBuilder;

public class RunningTeamBaseBuilder implements IBuilder<RunningTeam> {

	private int year;
	private String projectCode, teacherNumber, teamNumber, academicLevelCode;

	public RunningTeamBaseBuilder(int year, String projectCode, String teacherNumber, String teamNumber, String academicLevelCode) {
		this.year = year;
		this.projectCode = projectCode;
		this.teacherNumber = teacherNumber;
		this.teamNumber = teamNumber;
		this.academicLevelCode = academicLevelCode;
	}

	@Override
	public RunningTeam build() {
		RunningTeam runningTeam = new RunningTeam();
		runningTeam.setRunning(new RunningBaseBuilder(year, projectCode, teacherNumber).build());
		runningTeam.setTeam(new TeamBaseBuilder(teamNumber).build());
		runningTeam.setAcademicLevel(new AcademicLevelBaseBuilder(academicLevelCode).build());

		return runningTeam;
	}
}

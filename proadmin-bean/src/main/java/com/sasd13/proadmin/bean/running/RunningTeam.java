package com.sasd13.proadmin.bean.running;

import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;

public class RunningTeam {

	private Running running;
	private Team team;
	private AcademicLevel academicLevel;

	public Running getRunning() {
		return running;
	}

	public void setRunning(Running running) {
		this.running = running;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public AcademicLevel getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("RunningTeam [");
		builder.append("]");

		return builder.toString();
	}
}

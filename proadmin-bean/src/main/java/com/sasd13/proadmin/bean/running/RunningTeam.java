package com.sasd13.proadmin.bean.running;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;

public class RunningTeam {

	private Running running;
	private Team team;
	private AcademicLevel academicLevel;

	@JsonBackReference("reports")
	private List<Report> reports;

	public RunningTeam() {
		reports = new ArrayList<>();
	}

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

	public List<Report> getReports() {
		return reports;
	}

	boolean addReport(Report report) {
		return reports.add(report);
	}

	boolean removeReport(Report report) {
		boolean removed = reports.remove(report);

		if (removed) {
			report.setRunningTeam(null);
		}

		return removed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("RunningTeam [");
		builder.append("]");

		return builder.toString();
	}
}

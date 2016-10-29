package com.sasd13.proadmin.bean.running;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class RunningTeam {

	private Running running;
	private Team team;
	private AcademicLevel academicLevel;
	private List<Report> reports;

	public RunningTeam(Running running, Team team) {
		this.running = running;
		this.team = team;
		reports = new ArrayList<>();
	}

	public AcademicLevel getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(AcademicLevel academicLevel) {
		this.academicLevel = academicLevel;
	}

	public Running getRunning() {
		return running;
	}

	public Team getTeam() {
		return team;
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

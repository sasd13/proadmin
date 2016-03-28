package com.sasd13.proadmin.bean.running;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sasd13.proadmin.bean.member.Team;

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class RunningTeam {
	
	private long id;
	private List<Report> reports;
	private Running running;
	private Team team;
	
	public RunningTeam() {
		reports = new ArrayList<>();
	}
	
	public RunningTeam(Running running, Team team) {
		this();
		
		this.running = running;
		this.team = team;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public List<Report> getReports() {
		return reports;
	}
	
	public Running getRunning() {
		return running;
	}
	
	public Team getTeam() {
		return team;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("RunningTeam [");
		builder.append("id=" + getId());
		builder.append("]");
		
		return builder.toString();
	}
}

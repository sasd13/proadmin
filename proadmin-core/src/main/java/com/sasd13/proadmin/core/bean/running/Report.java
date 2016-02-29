package com.sasd13.proadmin.core.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Report {
	
	private long id;
	private Timestamp meetingDate;
	private int week;
	private String comment;
	private LeadEvaluation leadEvaluation;
	private List<IndividualEvaluation> individualEvaluations;
	private Team team;
	
	public Report() {}
	
	public Report(Team team) {
		this.team = team;
		
		leadEvaluation = new LeadEvaluation(this);
		individualEvaluations = new ArrayList<>();
		team.getReports().add(this);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Timestamp getMeetingDate() {
		return meetingDate;
	}
	
	public void setMeetingDate(Timestamp meetingDate) {
		this.meetingDate = meetingDate;
	}
	
	public int getWeek() {
		return week;
	}
	
	public void setWeek(int week) {
		this.week = week;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public LeadEvaluation getLeadEvaluation() {
		return leadEvaluation;
	}
	
	public List<IndividualEvaluation> getIndividualEvaluations() {
		return individualEvaluations;
	}
	
	public Team getTeam() {
		return team;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Report [");
		builder.append("id=" + getId());
		builder.append(", meetingDate=" + getMeetingDate());
		builder.append(", weak=" + getWeek());
		builder.append(", comment=" + getComment());
		builder.append("]");
		
		return builder.toString().trim();
	}
}

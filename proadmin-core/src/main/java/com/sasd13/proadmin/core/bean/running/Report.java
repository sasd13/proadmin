package com.sasd13.proadmin.core.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Report {
	
	private long id;
	private Timestamp dateMeeting;
	private int week;
	private String teamComment;
	private LeadEvaluation leadEvaluation;
	private List<IndividualEvaluation> individualEvaluations;
	private Team team;
	
	public Report() {
		leadEvaluation = new LeadEvaluation();
		individualEvaluations = new ArrayList<>();
		
		leadEvaluation.setReport(this);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Timestamp getDateMeeting() {
		return dateMeeting;
	}
	
	public void setDateMeeting(Timestamp dateMeeting) {
		this.dateMeeting = dateMeeting;
	}
	
	public int getWeek() {
		return week;
	}
	
	public void setWeek(int week) {
		this.week = week;
	}
	
	public String getTeamComment() {
		return teamComment;
	}
	
	public void setTeamComment(String teamComment) {
		this.teamComment = teamComment;
	}
	
	public LeadEvaluation getLeadEvaluation() {
		return leadEvaluation;
	}
	
	public void addIndividualEvaluation(IndividualEvaluation individualEvaluation) {
		individualEvaluations.add(individualEvaluation);
	}
	
	public void removeIndividualEvaluation(IndividualEvaluation individualEvaluation) {
		individualEvaluations.remove(individualEvaluation);
	}
	
	public IndividualEvaluation[] getIndividualEvaluations() {
		return individualEvaluations.toArray(new IndividualEvaluation[individualEvaluations.size()]);
	}
	
	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Report [");
		builder.append("id=" + getId());
		builder.append(", dateMeeting=" + getDateMeeting());
		builder.append(", weak=" + getWeek());
		builder.append(", teamComment=" + getTeamComment());
		builder.append("]");
		
		return builder.toString().trim();
	}
}

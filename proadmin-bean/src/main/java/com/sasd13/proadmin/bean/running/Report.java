package com.sasd13.proadmin.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Report {
	
	private long id;
	private Timestamp meetingDate;
	private int sessionNumber;
	private String comment;
	private LeadEvaluation leadEvaluation;
	private List<IndividualEvaluation> individualEvaluations;
	private RunningTeam runningTeam;
	
	public Report() {
		leadEvaluation = new LeadEvaluation(this);
		individualEvaluations = new ArrayList<>();
	}
	
	public Report(RunningTeam runningTeam) {
		this();
		
		//this.runningTeam = runningTeam; //Parsing circular association error
		runningTeam.getReports().add(this);
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
	
	public int getSessionNumber() {
		return sessionNumber;
	}
	
	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
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
	
	public RunningTeam getRunningTeam() {
		return runningTeam;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Report [");
		builder.append("id=" + getId());
		builder.append(", meetingDate=" + getMeetingDate());
		builder.append(", sessionNumber=" + getSessionNumber());
		builder.append(", comment=" + getComment());
		builder.append("]");
		
		return builder.toString();
	}
}

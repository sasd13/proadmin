package com.sasd13.proadmin.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Report {

	private RunningTeam runningTeam;
	private String number, comment;
	private Timestamp dateMeeting;
	private int session;
	private LeadEvaluation leadEvaluation;
	private List<IndividualEvaluation> individualEvaluations;

	public Report() {
		leadEvaluation = new LeadEvaluation();
		individualEvaluations = new ArrayList<>();

		leadEvaluation.setReport(this);
	}

	public RunningTeam getRunningTeam() {
		return runningTeam;
	}

	public void setRunningTeam(RunningTeam runningTeam) {
		this.runningTeam = runningTeam;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Timestamp getDateMeeting() {
		return dateMeeting;
	}

	public void setDateMeeting(Timestamp dateMeeting) {
		this.dateMeeting = dateMeeting;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
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

	public void setLeadEvaluation(LeadEvaluation leadEvaluation) {
		this.leadEvaluation = leadEvaluation;
	}

	public List<IndividualEvaluation> getIndividualEvaluations() {
		return individualEvaluations;
	}

	public void setIndividualEvaluations(List<IndividualEvaluation> individualEvaluations) {
		this.individualEvaluations = individualEvaluations;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Report [");
		builder.append("number=" + getNumber());
		builder.append(", dateMeeting=" + getDateMeeting());
		builder.append(", session=" + getSession());
		builder.append(", comment=" + getComment());
		builder.append("]");

		return builder.toString();
	}
}

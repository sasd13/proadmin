package com.sasd13.proadmin.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Report {

	@JsonManagedReference("runningteam")
	private RunningTeam runningTeam;

	private String number, comment;
	private Timestamp dateMeeting;
	private int session;

	@JsonBackReference("leadevaluation")
	private LeadEvaluation leadEvaluation;

	@JsonBackReference("individualevaluations")
	private List<IndividualEvaluation> individualEvaluations;

	public Report() {
		leadEvaluation = new LeadEvaluation(this);
		individualEvaluations = new ArrayList<>();
	}

	public Report(RunningTeam runningTeam) {
		this();

		this.runningTeam = runningTeam;

		runningTeam.addReport(this);
	}

	public RunningTeam getRunningTeam() {
		return runningTeam;
	}

	void setRunningTeam(RunningTeam runningTeam) {
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

	public List<IndividualEvaluation> getIndividualEvaluations() {
		return individualEvaluations;
	}

	boolean addIndividualEvaluation(IndividualEvaluation individualEvaluation) {
		return individualEvaluations.add(individualEvaluation);
	}

	boolean removeIndividualEvaluation(IndividualEvaluation individualEvaluation) {
		boolean removed = individualEvaluations.remove(individualEvaluation);

		if (removed) {
			individualEvaluation.setReport(null);
		}

		return removed;
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

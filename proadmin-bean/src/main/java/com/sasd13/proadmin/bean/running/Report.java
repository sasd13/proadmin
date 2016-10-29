package com.sasd13.proadmin.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Report {

	private RunningTeam runningTeam;
	private String number, comment;
	private Timestamp meetingDate;
	private int session;
	private LeadEvaluation leadEvaluation;
	private List<IndividualEvaluation> individualEvaluations;

	public Report() {
		leadEvaluation = new LeadEvaluation(this);
		individualEvaluations = new ArrayList<>();

		runningTeam.addReport(this);
	}

	public Report(RunningTeam runningTeam) {
		this();

		this.runningTeam = runningTeam;
	}

	public RunningTeam getRunningTeam() {
		return runningTeam;
	}

	void setRunningTeam(RunningTeam runningTeam) {
		this.runningTeam = runningTeam;
	}

	public Timestamp getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Timestamp meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getSession() {
		return session;
	}

	public void setSession(int session) {
		this.session = session;
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
		builder.append(", meetingDate=" + getMeetingDate());
		builder.append(", session=" + getSession());
		builder.append(", comment=" + getComment());
		builder.append("]");

		return builder.toString();
	}
}

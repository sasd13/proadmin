package com.sasd13.proadmin.bean.running;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

		this.runningTeam = runningTeam;
		runningTeam.addReport(this);
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

	public RunningTeam getRunningTeam() {
		return runningTeam;
	}

	void setRunningTeam(RunningTeam runningTeam) {
		this.runningTeam = runningTeam;
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

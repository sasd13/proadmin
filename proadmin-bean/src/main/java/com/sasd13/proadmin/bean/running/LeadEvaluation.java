package com.sasd13.proadmin.bean.running;

public class LeadEvaluation extends Evaluation {

	private float planningMark, communicationMark;
	private String planningComment, communicationComment;

	public float getPlanningMark() {
		return planningMark;
	}

	public void setPlanningMark(float planningMark) {
		this.planningMark = planningMark;
	}

	public float getCommunicationMark() {
		return communicationMark;
	}

	public void setCommunicationMark(float communicationMark) {
		this.communicationMark = communicationMark;
	}

	public String getPlanningComment() {
		return planningComment;
	}

	public void setPlanningComment(String planningComment) {
		this.planningComment = planningComment;
	}

	public String getCommunicationComment() {
		return communicationComment;
	}

	public void setCommunicationComment(String communicationComment) {
		this.communicationComment = communicationComment;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("LeadEvaluation [");
		builder.append("planningMark=" + planningMark);
		builder.append(", planningComment=" + planningComment);
		builder.append(", communicationMark=" + communicationMark);
		builder.append(", communicationComment=" + communicationComment);
		builder.append(", report=" + getReport());
		builder.append(", student=" + getStudent());
		builder.append("]");

		return builder.toString();
	}
}

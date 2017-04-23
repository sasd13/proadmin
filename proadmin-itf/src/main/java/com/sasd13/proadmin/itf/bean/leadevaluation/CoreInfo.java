package com.sasd13.proadmin.itf.bean.leadevaluation;

public class CoreInfo {

	private float planningMark, communicationMark;
	private String planningComment, communicationComment;

	public float getPlanningMark() {
		return planningMark;
	}

	public void setPlanningMark(float planningMark) {
		this.planningMark = planningMark;
	}

	public String getPlanningComment() {
		return planningComment;
	}

	public void setPlanningComment(String planningComment) {
		this.planningComment = planningComment;
	}

	public float getCommunicationMark() {
		return communicationMark;
	}

	public void setCommunicationMark(float communicationMark) {
		this.communicationMark = communicationMark;
	}

	public String getCommunicationComment() {
		return communicationComment;
	}

	public void setCommunicationComment(String communicationComment) {
		this.communicationComment = communicationComment;
	}
}

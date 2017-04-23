package com.sasd13.proadmin.ws.bean;

public class LeadEvaluation {

	private Report report;
	private Student student;
	private float planningMark, communicationMark;
	private String planningComment, communicationComment;

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

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
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		LeadEvaluation other = (LeadEvaluation) obj;

		if (report == null && other.report != null)
			return false;
		else if (!report.equals(other.report))
			return false;

		return true;
	}
}

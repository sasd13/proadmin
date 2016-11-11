package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.proadmin.bean.running.LeadEvaluation;

public class LeadEvaluationUpdateWrapper implements ILeadEvaluationUpdateWrapper {

	private LeadEvaluation leadEvaluation;
	private String reportNumber, studentNumber;

	@Override
	public LeadEvaluation getWrapped() {
		return leadEvaluation;
	}

	@Override
	public void setWrapped(LeadEvaluation leadEvaluation) {
		this.leadEvaluation = leadEvaluation;
	}

	@Override
	public String getReportNumber() {
		return reportNumber;
	}

	@Override
	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	@Override
	public String getStudentNumber() {
		return studentNumber;
	}

	@Override
	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
}

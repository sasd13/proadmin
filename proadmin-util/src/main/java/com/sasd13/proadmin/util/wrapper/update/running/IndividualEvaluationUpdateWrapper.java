package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public class IndividualEvaluationUpdateWrapper implements IIndividualEvaluationUpdateWrapper {

	private IndividualEvaluation individualEvaluation;
	private String reportNumber, studentNumber;

	@Override
	public IndividualEvaluation getWrapped() {
		return individualEvaluation;
	}

	@Override
	public void setWrapped(IndividualEvaluation individualEvaluation) {
		this.individualEvaluation = individualEvaluation;
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

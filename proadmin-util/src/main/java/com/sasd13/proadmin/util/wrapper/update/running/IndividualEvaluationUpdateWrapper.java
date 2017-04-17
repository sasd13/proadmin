package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public class IndividualEvaluationUpdateWrapper implements IUpdateWrapper<IndividualEvaluation> {

	private IndividualEvaluation individualEvaluation;
	private String reportNumber, studentIntermediary;

	@Override
	public IndividualEvaluation getWrapped() {
		return individualEvaluation;
	}

	@Override
	public void setWrapped(IndividualEvaluation individualEvaluation) {
		this.individualEvaluation = individualEvaluation;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getStudentIntermediary() {
		return studentIntermediary;
	}

	public void setStudentIntermediary(String studentIntermediary) {
		this.studentIntermediary = studentIntermediary;
	}
}

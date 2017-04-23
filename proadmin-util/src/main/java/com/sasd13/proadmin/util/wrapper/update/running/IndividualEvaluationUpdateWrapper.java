package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IIndividualEvaluation;

public class IndividualEvaluationUpdateWrapper implements IUpdateWrapper<IIndividualEvaluation> {

	private IIndividualEvaluation iIndividualEvaluation;
	private String reportNumber, studentIntermediary;

	@Override
	public IIndividualEvaluation getWrapped() {
		return iIndividualEvaluation;
	}

	@Override
	public void setWrapped(IIndividualEvaluation iIndividualEvaluation) {
		this.iIndividualEvaluation = iIndividualEvaluation;
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

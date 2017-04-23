package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.ILeadEvaluation;

public class LeadEvaluationUpdateWrapper implements IUpdateWrapper<ILeadEvaluation> {

	private ILeadEvaluation iLeadEvaluation;
	private String reportNumber;

	@Override
	public ILeadEvaluation getWrapped() {
		return iLeadEvaluation;
	}

	@Override
	public void setWrapped(ILeadEvaluation iLeadEvaluation) {
		this.iLeadEvaluation = iLeadEvaluation;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
}

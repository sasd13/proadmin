package com.sasd13.proadmin.util.wrapper.update.running;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.LeadEvaluation;

public class LeadEvaluationUpdateWrapper implements IUpdateWrapper<LeadEvaluation> {

	private LeadEvaluation leadEvaluation;
	private String reportNumber;

	@Override
	public LeadEvaluation getWrapped() {
		return leadEvaluation;
	}

	@Override
	public void setWrapped(LeadEvaluation leadEvaluation) {
		this.leadEvaluation = leadEvaluation;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}
}

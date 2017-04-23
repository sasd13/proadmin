package com.sasd13.proadmin.ws.bean.update;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.ws.bean.LeadEvaluation;

public class LeadEvaluationUpdate implements IUpdateWrapper<LeadEvaluation> {

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

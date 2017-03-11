package com.sasd13.proadmin.util.wrapper.create;

import java.util.List;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;

public class ReportCreateWrapper implements IReportCreateWrapper {

	private Report report;
	private LeadEvaluation leadEvaluation;
	private List<IndividualEvaluation> individualEvaluations;

	@Override
	public Report getReport() {
		return report;
	}

	@Override
	public void setReport(Report report) {
		this.report = report;
	}

	@Override
	public LeadEvaluation getLeadEvaluation() {
		return leadEvaluation;
	}

	@Override
	public void setLeadEvaluation(LeadEvaluation leadEvaluation) {
		this.leadEvaluation = leadEvaluation;
	}

	@Override
	public List<IndividualEvaluation> getIndividualEvaluations() {
		return individualEvaluations;
	}

	@Override
	public void setIndividualEvaluations(List<IndividualEvaluation> individualEvaluations) {
		this.individualEvaluations = individualEvaluations;
	}
}

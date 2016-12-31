package com.sasd13.proadmin.util.wrapper.update.running;

import java.util.List;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;

public class ReportUpdateWrapper implements IReportUpdateWrapper {

	private Report report;
	private String number;
	private IUpdateWrapper<LeadEvaluation> leadEvaluationUpdateWrapper;
	private List<IUpdateWrapper<IndividualEvaluation>> individualEvaluationUpdateWrappers;

	@Override
	public Report getWrapped() {
		return report;
	}

	@Override
	public void setWrapped(Report report) {
		this.report = report;
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public IUpdateWrapper<LeadEvaluation> getLeadEvaluationUpdateWrapper() {
		return leadEvaluationUpdateWrapper;
	}

	@Override
	public void setLeadEvaluationUpdateWrapper(IUpdateWrapper<LeadEvaluation> leadEvaluationUpdateWrapper) {
		this.leadEvaluationUpdateWrapper = leadEvaluationUpdateWrapper;
	}

	@Override
	public List<IUpdateWrapper<IndividualEvaluation>> getIndividualEvaluationUpdateWrappers() {
		return individualEvaluationUpdateWrappers;
	}

	@Override
	public void setIndividualEvaluationUpdateWrappers(List<IUpdateWrapper<IndividualEvaluation>> individualEvaluationUpdateWrappers) {
		this.individualEvaluationUpdateWrappers = individualEvaluationUpdateWrappers;
	}
}

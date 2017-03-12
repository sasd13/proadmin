package com.sasd13.proadmin.util.wrapper.update.running;

import java.util.List;

import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;

public class ReportUpdateWrapper implements IUpdateWrapper<Report> {

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public IUpdateWrapper<LeadEvaluation> getLeadEvaluationUpdateWrapper() {
		return leadEvaluationUpdateWrapper;
	}

	public void setLeadEvaluationUpdateWrapper(IUpdateWrapper<LeadEvaluation> leadEvaluationUpdateWrapper) {
		this.leadEvaluationUpdateWrapper = leadEvaluationUpdateWrapper;
	}

	public List<IUpdateWrapper<IndividualEvaluation>> getIndividualEvaluationUpdateWrappers() {
		return individualEvaluationUpdateWrappers;
	}

	public void setIndividualEvaluationUpdateWrappers(List<IUpdateWrapper<IndividualEvaluation>> individualEvaluationUpdateWrappers) {
		this.individualEvaluationUpdateWrappers = individualEvaluationUpdateWrappers;
	}
}

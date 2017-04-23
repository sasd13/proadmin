package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.LeadEvaluation;
import com.sasd13.proadmin.backend.dao.dto.LeadEvaluationDTO;

public class LeadEvaluationAdapterD2B implements IAdapter<LeadEvaluationDTO, LeadEvaluation> {

	private ReportAdapterD2B reportAdapter;
	private StudentAdapterD2B studentAdapter;

	public LeadEvaluationAdapterD2B() {
		reportAdapter = new ReportAdapterD2B();
		studentAdapter = new StudentAdapterD2B();
	}

	@Override
	public LeadEvaluation adapt(LeadEvaluationDTO s) {
		LeadEvaluation t = new LeadEvaluation();

		t.setId(s.getId());
		t.setPlanningMark(s.getPlanningMark());
		t.setPlanningComment(s.getPlanningComment());
		t.setCommunicationMark(s.getCommunicationMark());
		t.setCommunicationComment(s.getCommunicationComment());
		t.setReport(reportAdapter.adapt(s.getReport()));
		t.setStudent(studentAdapter.adapt(s.getStudent()));

		return t;
	}
}

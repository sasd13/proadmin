package com.sasd13.proadmin.ws2.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.ws2.dao.dto.LeadEvaluationDTO;

public class LeadEvaluationDTOAdapter implements IAdapter<LeadEvaluationDTO, LeadEvaluation> {

	private ReportDTOAdapter reportDTOAdapter;
	private StudentDTOAdapter studentDTOAdapter;

	public LeadEvaluationDTOAdapter() {
		reportDTOAdapter = new ReportDTOAdapter();
		studentDTOAdapter = new StudentDTOAdapter();
	}

	@Override
	public LeadEvaluation adapt(LeadEvaluationDTO source) {
		LeadEvaluation target = new LeadEvaluation();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(LeadEvaluationDTO source, LeadEvaluation target) {
		target.setPlanningMark(source.getPlanningMark());
		target.setPlanningComment(source.getPlanningComment());
		target.setCommunicationMark(source.getCommunicationMark());
		target.setCommunicationComment(source.getCommunicationComment());
		target.setReport(reportDTOAdapter.adapt(source.getReport()));
		target.setStudent(studentDTOAdapter.adapt(source.getStudent()));
	}
}

package com.sasd13.proadmin.ws2.util.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.ws2.db.dto.LeadEvaluationDTO;

public class LeadEvaluationDTOAdapter implements IAdapter<LeadEvaluationDTO, LeadEvaluation> {

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
		target.setReport(new ReportDTOAdapter().adapt(source.getReport()));
		target.setStudent(new StudentDTOAdapter().adapt(source.getStudent()));
	}
}

package com.sasd13.proadmin.ws2.db.dto.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.ws2.db.dto.IndividualEvaluationDTO;
import com.sasd13.proadmin.ws2.db.dto.ReportDTO;

public class IndividualEvaluationDTOAdapter implements IAdapter<IndividualEvaluationDTO, IndividualEvaluation> {
	
	@Override
	public IndividualEvaluation adapt(IndividualEvaluationDTO source) {
		IndividualEvaluation target = new IndividualEvaluation();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(IndividualEvaluationDTO source, IndividualEvaluation target) {
		target.setMark(source.getMark());
		target.setReport(source.getReport());
		target.setStudent(source.getStudent());
	}
	
	private Report getReport(ReportDTO dto) {
		Report report = new Report();
		
		report.setNumber(dto.getCode());
		report.setDateMeeting(dto.getDateMeeting());
		report.setSession(dto.getSession());
		report.setComment(dto.getComment());
		report.set
		
		return report;
	}
}

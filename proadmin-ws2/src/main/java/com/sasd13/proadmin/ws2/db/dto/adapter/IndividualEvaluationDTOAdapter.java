package com.sasd13.proadmin.ws2.db.dto.adapter;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.ws2.db.dto.IndividualEvaluationDTO;

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
		target.setReport(new ReportDTOAdapter().adapt(source.getReport()));
		target.setStudent(new StudentDTOAdapter().adapt(source.getStudent()));
	}
}

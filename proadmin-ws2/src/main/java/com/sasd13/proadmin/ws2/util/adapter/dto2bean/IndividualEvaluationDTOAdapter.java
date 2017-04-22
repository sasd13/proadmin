package com.sasd13.proadmin.ws2.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.ws2.dao.dto.IndividualEvaluationDTO;

public class IndividualEvaluationDTOAdapter implements IAdapter<IndividualEvaluationDTO, IndividualEvaluation> {

	private ReportDTOAdapter reportDTOAdapter;
	private StudentDTOAdapter studentDTOAdapter;

	public IndividualEvaluationDTOAdapter() {
		reportDTOAdapter = new ReportDTOAdapter();
		studentDTOAdapter = new StudentDTOAdapter();
	}

	@Override
	public IndividualEvaluation adapt(IndividualEvaluationDTO source) {
		IndividualEvaluation target = new IndividualEvaluation();

		adapt(source, target);

		return target;
	}

	@Override
	public void adapt(IndividualEvaluationDTO source, IndividualEvaluation target) {
		target.setMark(source.getMark());
		target.setReport(reportDTOAdapter.adapt(source.getReport()));
		target.setStudent(studentDTOAdapter.adapt(source.getStudent()));
	}
}

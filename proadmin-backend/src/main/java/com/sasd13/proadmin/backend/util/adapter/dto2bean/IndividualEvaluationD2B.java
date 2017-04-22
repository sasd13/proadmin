package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.dao.dto.IndividualEvaluationDTO;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;

public class IndividualEvaluationD2B implements IAdapter<IndividualEvaluationDTO, IndividualEvaluation> {

	private ReportDTOAdapter reportDTOAdapter;
	private StudentDTOAdapter studentDTOAdapter;

	public IndividualEvaluationD2B() {
		reportDTOAdapter = new ReportDTOAdapter();
		studentDTOAdapter = new StudentDTOAdapter();
	}

	@Override
	public IndividualEvaluation adapt(IndividualEvaluationDTO s) {
		IndividualEvaluation t = new IndividualEvaluation();

		t.setMark(s.getMark());
		t.setReport(reportDTOAdapter.adapt(s.getReport()));
		t.setStudent(studentDTOAdapter.adapt(s.getStudent()));

		return t;
	}
}

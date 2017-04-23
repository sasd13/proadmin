package com.sasd13.proadmin.backend.util.adapter.dto2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.IndividualEvaluation;
import com.sasd13.proadmin.backend.dao.dto.IndividualEvaluationDTO;

public class IndividualEvaluationAdapterD2B implements IAdapter<IndividualEvaluationDTO, IndividualEvaluation> {

	private ReportAdapterD2B reportAdapter;
	private StudentAdapterD2B studentAdapter;

	public IndividualEvaluationAdapterD2B() {
		reportAdapter = new ReportAdapterD2B();
		studentAdapter = new StudentAdapterD2B();
	}

	@Override
	public IndividualEvaluation adapt(IndividualEvaluationDTO s) {
		IndividualEvaluation t = new IndividualEvaluation();

		t.setId(s.getId());
		t.setMark(s.getMark());
		t.setReport(reportAdapter.adapt(s.getReport()));
		t.setStudent(studentAdapter.adapt(s.getStudent()));

		return t;
	}
}

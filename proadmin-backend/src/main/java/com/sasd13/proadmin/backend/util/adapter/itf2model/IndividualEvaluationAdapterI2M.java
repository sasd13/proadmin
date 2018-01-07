package com.sasd13.proadmin.backend.util.adapter.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.entity.IndividualEvaluation;
import com.sasd13.proadmin.backend.entity.Report;
import com.sasd13.proadmin.backend.entity.Student;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

public class IndividualEvaluationAdapterI2M implements IAdapter<IndividualEvaluationBean, IndividualEvaluation> {

	@Override
	public IndividualEvaluation adapt(IndividualEvaluationBean s) {
		IndividualEvaluation t = new IndividualEvaluation();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		t.setMark(Float.valueOf(s.getCoreInfo().getMark()));

		Report report = new Report();
		report.setId(Long.valueOf(s.getLinkedReport().getId()));
		t.setReport(report);

		Student student = new Student();
		student.setId(Long.valueOf(s.getLinkedStudent().getId()));
		t.setStudent(student);

		return t;
	}
}

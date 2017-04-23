package com.sasd13.proadmin.backend.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.IndividualEvaluation;
import com.sasd13.proadmin.backend.bean.Report;
import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

public class IndividualEvaluationAdapterI2B implements IAdapter<IndividualEvaluationBean, IndividualEvaluation> {

	@Override
	public IndividualEvaluation adapt(IndividualEvaluationBean s) {
		IndividualEvaluation t = new IndividualEvaluation();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		t.setMark(Float.valueOf(s.getCoreInfo().getMark()));

		Report report = new Report();
		report.setId(Long.valueOf(s.getId().getId()));
		t.setReport(report);

		Student student = new Student();
		student.setId(Long.valueOf(s.getLinkedStudent().getId()));
		t.setStudent(student);

		return t;
	}
}

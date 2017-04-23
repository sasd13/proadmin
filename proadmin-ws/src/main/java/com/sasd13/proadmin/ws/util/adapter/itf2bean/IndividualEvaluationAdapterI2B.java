package com.sasd13.proadmin.ws.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.ws.bean.IndividualEvaluation;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.Student;

public class IndividualEvaluationAdapterI2B implements IAdapter<IndividualEvaluationBean, IndividualEvaluation> {

	@Override
	public IndividualEvaluation adapt(IndividualEvaluationBean s) {
		IndividualEvaluation t = new IndividualEvaluation();

		t.setMark(s.getCoreInfo().getMark());

		Report report = new Report();
		report.setNumber(s.getLinkedReport().getNumber());
		t.setReport(report);

		Student student = new Student();
		student.setIntermediary(s.getLinkedStudent().getIntermediary());
		t.setStudent(student);

		return t;
	}
}

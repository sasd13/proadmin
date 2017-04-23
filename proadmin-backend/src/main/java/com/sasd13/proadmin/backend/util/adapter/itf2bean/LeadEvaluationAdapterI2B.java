package com.sasd13.proadmin.backend.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.LeadEvaluation;
import com.sasd13.proadmin.backend.bean.Report;
import com.sasd13.proadmin.backend.bean.Student;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

public class LeadEvaluationAdapterI2B implements IAdapter<LeadEvaluationBean, LeadEvaluation> {

	@Override
	public LeadEvaluation adapt(LeadEvaluationBean s) {
		LeadEvaluation t = new LeadEvaluation();

		if (s.getId() != null) {
			t.setId(Long.valueOf(s.getId().getId()));
		}

		t.setPlanningMark(Float.valueOf(s.getCoreInfo().getPlanningMark()));
		t.setPlanningComment(s.getCoreInfo().getPlanningComment());
		t.setCommunicationMark(Float.valueOf(s.getCoreInfo().getCommunicationMark()));
		t.setCommunicationComment(s.getCoreInfo().getCommunicationComment());

		Report report = new Report();
		report.setId(Long.valueOf(s.getLinkedReport().getId()));
		t.setReport(report);

		Student student = new Student();
		student.setId(Long.valueOf(s.getLinkedStudent().getId()));
		t.setStudent(student);

		return t;
	}
}

package com.sasd13.proadmin.ws.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.ws.bean.LeadEvaluation;
import com.sasd13.proadmin.ws.bean.Report;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.bean.update.LeadEvaluationUpdate;

public class LeadEvaluationUpdateAdapterI2B implements IAdapter<LeadEvaluationBean, LeadEvaluationUpdate> {

	@Override
	public LeadEvaluationUpdate adapt(LeadEvaluationBean s) {
		LeadEvaluationUpdate t = new LeadEvaluationUpdate();

		t.setReportNumber(s.getId().getId());

		LeadEvaluation leadEvaluation = new LeadEvaluation();
		leadEvaluation.setPlanningMark(s.getCoreInfo().getPlanningMark());
		leadEvaluation.setPlanningComment(s.getCoreInfo().getPlanningComment());
		leadEvaluation.setCommunicationMark(s.getCoreInfo().getCommunicationMark());
		leadEvaluation.setCommunicationComment(s.getCoreInfo().getCommunicationComment());
		t.setWrapped(leadEvaluation);

		Report report = new Report();
		report.setNumber(s.getLinkedReport().getNumber());
		leadEvaluation.setReport(report);

		Student student = new Student();
		student.setIntermediary(s.getLinkedStudent().getIntermediary());
		leadEvaluation.setStudent(student);

		return t;
	}
}

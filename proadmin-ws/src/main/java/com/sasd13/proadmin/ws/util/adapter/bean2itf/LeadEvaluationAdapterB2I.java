package com.sasd13.proadmin.ws.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.leadevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.leadevaluation.Id;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LinkedId;
import com.sasd13.proadmin.ws.bean.LeadEvaluation;

public class LeadEvaluationAdapterB2I implements IAdapter<LeadEvaluation, LeadEvaluationBean> {

	@Override
	public LeadEvaluationBean adapt(LeadEvaluation s) {
		LeadEvaluationBean t = new LeadEvaluationBean();

		Id id = new Id();
		t.setId(id);

		LinkedId linkedId = new LinkedId();
		linkedId.setReportNumber(s.getReport().getNumber());
		id.setLinkedId(linkedId);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setPlanningMark(s.getPlanningMark());
		coreInfo.setPlanningComment(s.getPlanningComment());
		coreInfo.setCommunicationMark(s.getCommunicationMark());
		coreInfo.setCommunicationComment(s.getCommunicationComment());
		t.setCoreInfo(coreInfo);

		LinkedReport linkedReport = new LinkedReport();
		linkedReport.setNumber(s.getReport().getNumber());
		t.setLinkedReport(linkedReport);

		LinkedStudent linkedStudent = new LinkedStudent();
		linkedStudent.setIntermediary(s.getStudent().getIntermediary());
		t.setLinkedStudent(linkedStudent);

		return t;
	}
}

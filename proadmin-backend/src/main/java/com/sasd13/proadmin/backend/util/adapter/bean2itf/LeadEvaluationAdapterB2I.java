package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.LeadEvaluation;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedInfo;
import com.sasd13.proadmin.itf.bean.leadevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

public class LeadEvaluationAdapterB2I implements IAdapter<LeadEvaluation, LeadEvaluationBean> {

	@Override
	public LeadEvaluationBean adapt(LeadEvaluation s) {
		LeadEvaluationBean t = new LeadEvaluationBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setPlanningMark(String.valueOf(s.getPlanningMark()));
		coreInfo.setPlanningComment(s.getPlanningComment());
		coreInfo.setCommunicationMark(String.valueOf(s.getCommunicationMark()));
		coreInfo.setCommunicationComment(s.getCommunicationComment());
		t.setCoreInfo(coreInfo);

		LinkedInfo linkedReport = new LinkedInfo();
		linkedReport.setId(String.valueOf(s.getId()));
		t.setLinkedReport(linkedReport);

		LinkedInfo linkedStudent = new LinkedInfo();
		linkedStudent.setId(String.valueOf(s.getId()));
		t.setLinkedStudent(linkedStudent);

		return t;
	}
}

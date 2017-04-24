package com.sasd13.proadmin.ws.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.leadevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.ws.bean.LeadEvaluation;

public class LeadEvaluationAdapterB2I implements IAdapter<LeadEvaluation, LeadEvaluationBean> {

	@Override
	public LeadEvaluationBean adapt(LeadEvaluation s) {
		LeadEvaluationBean t = new LeadEvaluationBean();

		Id id = new Id();
		id.setId(s.getReport().getNumber());
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setPlanningMark(s.getPlanningMark());
		coreInfo.setPlanningComment(s.getPlanningComment());
		coreInfo.setCommunicationMark(s.getCommunicationMark());
		coreInfo.setCommunicationComment(s.getCommunicationComment());
		t.setCoreInfo(coreInfo);

		return t;
	}
}

package com.sasd13.proadmin.backend.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.bean.IndividualEvaluation;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedInfo;
import com.sasd13.proadmin.itf.bean.individualevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

public class IndividualEvaluationAdapterB2I implements IAdapter<IndividualEvaluation, IndividualEvaluationBean> {

	@Override
	public IndividualEvaluationBean adapt(IndividualEvaluation s) {
		IndividualEvaluationBean t = new IndividualEvaluationBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setMark(s.getMark());
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

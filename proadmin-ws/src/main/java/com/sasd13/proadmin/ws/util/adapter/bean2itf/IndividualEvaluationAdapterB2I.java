package com.sasd13.proadmin.ws.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.individualevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.individualevaluation.Id;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.LinkedId;
import com.sasd13.proadmin.ws.bean.IndividualEvaluation;

public class IndividualEvaluationAdapterB2I implements IAdapter<IndividualEvaluation, IndividualEvaluationBean> {

	@Override
	public IndividualEvaluationBean adapt(IndividualEvaluation s) {
		IndividualEvaluationBean t = new IndividualEvaluationBean();

		Id id = new Id();
		t.setId(id);

		LinkedId linkedId = new LinkedId();
		linkedId.setReportNumber(s.getReport().getNumber());
		linkedId.setStudentIntermediary(s.getStudent().getIntermediary());
		id.setLinkedId(linkedId);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setMark(s.getMark());
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

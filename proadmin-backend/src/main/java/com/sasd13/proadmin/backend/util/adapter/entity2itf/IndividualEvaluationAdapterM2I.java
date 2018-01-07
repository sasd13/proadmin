package com.sasd13.proadmin.backend.util.adapter.entity2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.backend.entity.IndividualEvaluation;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.individualevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.individualevaluation.Id;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

public class IndividualEvaluationAdapterM2I implements IAdapter<IndividualEvaluation, IndividualEvaluationBean> {

	@Override
	public IndividualEvaluationBean adapt(IndividualEvaluation s) {
		IndividualEvaluationBean t = new IndividualEvaluationBean();

		Id id = new Id();
		id.setId(String.valueOf(s.getId()));
		t.setId(id);

		CoreInfo coreInfo = new CoreInfo();
		coreInfo.setMark(s.getMark());
		t.setCoreInfo(coreInfo);

		LinkedReport linkedReport = new LinkedReport();
		linkedReport.setId(String.valueOf(s.getReport().getId()));
		linkedReport.setNumber(s.getReport().getNumber());
		t.setLinkedReport(linkedReport);

		LinkedStudent linkedStudent = new LinkedStudent();
		linkedStudent.setId(String.valueOf(s.getStudent().getId()));
		linkedStudent.setIntermediary(s.getStudent().getIntermediary());
		linkedStudent.setFirstName(s.getStudent().getFirstName());
		linkedStudent.setLastName(s.getStudent().getLastName());
		t.setLinkedStudent(linkedStudent);

		return t;
	}
}

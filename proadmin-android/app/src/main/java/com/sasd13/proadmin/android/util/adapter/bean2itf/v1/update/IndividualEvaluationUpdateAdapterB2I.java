package com.sasd13.proadmin.android.util.adapter.bean2itf.v1.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.IndividualEvaluationUpdate;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.individualevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.individualevaluation.Id;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.LinkedId;

public class IndividualEvaluationUpdateAdapterB2I implements IAdapter<IndividualEvaluationUpdate, IndividualEvaluationBean> {

    @Override
    public IndividualEvaluationBean adapt(IndividualEvaluationUpdate s) {
        IndividualEvaluationBean t = new IndividualEvaluationBean();

        Id id = new Id();
        t.setId(id);

        LinkedId linkedId = new LinkedId();
        linkedId.setReportNumber(s.getReportNumber());
        linkedId.setStudentIntermediary(s.getStudentIntermediary());
        id.setLinkedId(linkedId);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setMark(s.getWrapped().getMark());
        t.setCoreInfo(coreInfo);

        LinkedReport linkedReport = new LinkedReport();
        linkedReport.setNumber(s.getWrapped().getReport().getNumber());
        t.setLinkedReport(linkedReport);

        LinkedStudent linkedStudent = new LinkedStudent();
        linkedStudent.setIntermediary(s.getWrapped().getStudent().getIntermediary());
        t.setLinkedStudent(linkedStudent);

        return t;
    }
}

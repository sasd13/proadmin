package com.sasd13.proadmin.android.util.adapter.bean2itf.v1.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.update.LeadEvaluationUpdate;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.leadevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.leadevaluation.Id;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LinkedId;

public class LeadEvaluationUpdateAdapterB2I implements IAdapter<LeadEvaluationUpdate, LeadEvaluationBean> {

    @Override
    public LeadEvaluationBean adapt(LeadEvaluationUpdate s) {
        LeadEvaluationBean t = new LeadEvaluationBean();

        Id id = new Id();
        t.setId(id);

        LinkedId linkedId = new LinkedId();
        linkedId.setReportNumber(s.getReportNumber());
        id.setLinkedId(linkedId);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setPlanningMark(s.getWrapped().getPlanningMark());
        coreInfo.setPlanningComment(s.getWrapped().getPlanningComment());
        coreInfo.setCommunicationMark(s.getWrapped().getCommunicationMark());
        coreInfo.setCommunicationComment(s.getWrapped().getCommunicationComment());
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

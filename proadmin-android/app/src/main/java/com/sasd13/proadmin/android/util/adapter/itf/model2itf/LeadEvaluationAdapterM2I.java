package com.sasd13.proadmin.android.util.adapter.itf.model2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.model.LeadEvaluation;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.leadevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.leadevaluation.Id;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

public class LeadEvaluationAdapterM2I implements IAdapter<LeadEvaluation, LeadEvaluationBean> {

    @Override
    public LeadEvaluationBean adapt(LeadEvaluation s) {
        LeadEvaluationBean t = new LeadEvaluationBean();

        Id id = new Id();
        id.setId(String.valueOf(s.getId()));
        t.setId(id);

        CoreInfo coreInfo = new CoreInfo();
        coreInfo.setPlanningMark(s.getPlanningMark());
        coreInfo.setPlanningComment(s.getPlanningComment());
        coreInfo.setCommunicationMark(s.getCommunicationMark());
        coreInfo.setCommunicationComment(s.getCommunicationComment());
        t.setCoreInfo(coreInfo);

        LinkedReport linkedReport = new LinkedReport();
        linkedReport.setId(String.valueOf(s.getReport().getId()));
        t.setLinkedReport(linkedReport);

        LinkedStudent linkedStudent = new LinkedStudent();
        linkedStudent.setId(String.valueOf(s.getStudent().getId()));
        t.setLinkedStudent(linkedStudent);

        return t;
    }
}

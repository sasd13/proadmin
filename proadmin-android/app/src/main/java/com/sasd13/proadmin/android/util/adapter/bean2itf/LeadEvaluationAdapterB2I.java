package com.sasd13.proadmin.android.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.itf.bean.leadevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.leadevaluation.Id;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;
import com.sasd13.proadmin.itf.bean.leadevaluation.LinkedId;

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

        return t;
    }
}

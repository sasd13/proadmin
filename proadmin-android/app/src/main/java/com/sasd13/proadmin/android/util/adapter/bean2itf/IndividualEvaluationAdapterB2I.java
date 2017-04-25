package com.sasd13.proadmin.android.util.adapter.bean2itf;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.itf.bean.individualevaluation.CoreInfo;
import com.sasd13.proadmin.itf.bean.individualevaluation.Id;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;
import com.sasd13.proadmin.itf.bean.individualevaluation.LinkedId;

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

        return t;
    }
}

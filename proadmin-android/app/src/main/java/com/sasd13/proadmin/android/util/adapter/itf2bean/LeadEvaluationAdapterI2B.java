package com.sasd13.proadmin.android.util.adapter.itf2bean;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

public class LeadEvaluationAdapterI2B implements IAdapter<LeadEvaluationBean, LeadEvaluation> {

    @Override
    public LeadEvaluation adapt(LeadEvaluationBean s) {
        LeadEvaluation t = new LeadEvaluation();

        t.setId(Long.valueOf(s.getId().getId()));
        t.setPlanningMark(Float.valueOf(s.getCoreInfo().getPlanningMark()));
        t.setPlanningComment(s.getCoreInfo().getPlanningComment());
        t.setCommunicationMark(Float.valueOf(s.getCoreInfo().getCommunicationMark()));
        t.setCommunicationComment(s.getCoreInfo().getCommunicationComment());

        Report report = new Report();
        report.setId(Long.valueOf(s.getLinkedReport().getId()));
        report.setNumber(s.getLinkedReport().getNumber());
        t.setReport(report);

        Student student = new Student();
        student.setId(Long.valueOf(s.getLinkedStudent().getId()));
        student.setIntermediary(s.getLinkedStudent().getIntermediary());
        t.setStudent(student);

        return t;
    }
}

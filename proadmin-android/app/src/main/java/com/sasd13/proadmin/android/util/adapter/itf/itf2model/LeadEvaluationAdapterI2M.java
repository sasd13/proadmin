package com.sasd13.proadmin.android.util.adapter.itf.itf2model;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.model.LeadEvaluation;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.model.Student;
import com.sasd13.proadmin.itf.bean.leadevaluation.LeadEvaluationBean;

public class LeadEvaluationAdapterI2M implements IAdapter<LeadEvaluationBean, LeadEvaluation> {

    @Override
    public LeadEvaluation adapt(LeadEvaluationBean s) {
        LeadEvaluation t = new LeadEvaluation();

        t.setId(Long.valueOf(s.getId().getId()));
        t.setPlanningMark(s.getCoreInfo().getPlanningMark());
        t.setPlanningComment(s.getCoreInfo().getPlanningComment());
        t.setCommunicationMark(s.getCoreInfo().getCommunicationMark());
        t.setCommunicationComment(s.getCoreInfo().getCommunicationComment());

        Report report = new Report();
        report.setId(Long.valueOf(s.getLinkedReport().getId()));
        report.setNumber(s.getLinkedReport().getNumber());
        t.setReport(report);

        Student student = new Student();
        student.setId(Long.valueOf(s.getLinkedStudent().getId()));
        student.setIntermediary(s.getLinkedStudent().getIntermediary());
        student.setFirstName(s.getLinkedStudent().getFirstName());
        student.setLastName(s.getLinkedStudent().getLastName());
        t.setStudent(student);

        return t;
    }
}

package com.sasd13.proadmin.android.util.adapter.itf2bean.v1;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

public class IndividualEvaluationAdapterI2B implements IAdapter<IndividualEvaluationBean, IndividualEvaluation> {

    @Override
    public IndividualEvaluation adapt(IndividualEvaluationBean s) {
        IndividualEvaluation t = new IndividualEvaluation();

        t.setMark(s.getCoreInfo().getMark());

        Report report = new Report();
        report.setNumber(s.getLinkedReport().getNumber());
        t.setReport(report);

        Student student = new Student();
        student.setIntermediary(s.getLinkedStudent().getIntermediary());
        t.setStudent(student);

        return t;
    }
}

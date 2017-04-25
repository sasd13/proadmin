package com.sasd13.proadmin.android.util.adapter.itf2bean.update;

import com.sasd13.javaex.pattern.adapter.IAdapter;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.Student;
import com.sasd13.proadmin.android.bean.update.IndividualEvaluationUpdate;
import com.sasd13.proadmin.itf.bean.individualevaluation.IndividualEvaluationBean;

public class IndividualEvaluationUpdateAdapterI2B implements IAdapter<IndividualEvaluationBean, IndividualEvaluationUpdate> {

    @Override
    public IndividualEvaluationUpdate adapt(IndividualEvaluationBean s) {
        IndividualEvaluationUpdate t = new IndividualEvaluationUpdate();

        t.setReportNumber(s.getId().getLinkedId().getReportNumber());
        t.setStudentIntermediary(s.getId().getLinkedId().getStudentIntermediary());

        IndividualEvaluation individualEvaluation = new IndividualEvaluation();
        individualEvaluation.setMark(s.getCoreInfo().getMark());
        t.setWrapped(individualEvaluation);

        Report report = new Report();
        report.setNumber(s.getLinkedReport().getNumber());
        individualEvaluation.setReport(report);

        Student student = new Student();
        student.setIntermediary(s.getLinkedStudent().getIntermediary());
        individualEvaluation.setStudent(student);

        return t;
    }
}

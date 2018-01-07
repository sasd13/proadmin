package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.model.IndividualEvaluation;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.model.Student;

/**
 * Created by ssaidali2 on 02/12/2016.
 */
public class NewIndividualEvaluationBuilder implements IBuilder<IndividualEvaluation> {

    private Report report;
    private Student student;

    public NewIndividualEvaluationBuilder(Report report, Student student) {
        this.report = report;
        this.student = student;
    }

    @Override
    public IndividualEvaluation build() {
        IndividualEvaluation individualEvaluation = new IndividualEvaluation();

        individualEvaluation.setReport(report);
        individualEvaluation.setStudent(student);

        return individualEvaluation;
    }
}

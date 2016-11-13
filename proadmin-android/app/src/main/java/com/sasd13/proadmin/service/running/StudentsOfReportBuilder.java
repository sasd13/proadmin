package com.sasd13.proadmin.service.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 13/11/2016.
 */

public class StudentsOfReportBuilder implements IBuilder<List<Student>> {

    private Report report;

    public StudentsOfReportBuilder(Report report) {
        this.report = report;
    }

    @Override
    public List<Student> build() {
        List<Student> students = new ArrayList<>();

        for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
            students.add(individualEvaluation.getStudent());
        }

        return students;
    }
}

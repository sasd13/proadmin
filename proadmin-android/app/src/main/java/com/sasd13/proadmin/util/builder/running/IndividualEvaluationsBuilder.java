package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 29/11/2016.
 */

public class IndividualEvaluationsBuilder implements IBuilder<List<IndividualEvaluation>> {

    private Report report;
    private List<StudentTeam> studentTeams;

    public IndividualEvaluationsBuilder(Report report, List<StudentTeam> studentTeams) {
        this.report = report;
        this.studentTeams = studentTeams;
    }

    @Override
    public List<IndividualEvaluation> build() {
        List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

        IndividualEvaluation individualEvaluation;

        for (StudentTeam studentTeam : studentTeams) {
            if (studentTeam.getTeam().getNumber().equalsIgnoreCase(report.getRunningTeam().getTeam().getNumber())) {
                individualEvaluation = new IndividualEvaluation();

                individualEvaluation.setReport(report);
                individualEvaluation.setStudent(studentTeam.getStudent());

                individualEvaluations.add(individualEvaluation);
            }
        }

        return individualEvaluations;
    }
}

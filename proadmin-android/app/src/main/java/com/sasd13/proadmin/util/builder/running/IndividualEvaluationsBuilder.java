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

    private List<StudentTeam> studentTeams;
    private Report report;

    public IndividualEvaluationsBuilder(List<StudentTeam> studentTeams) {
        this.studentTeams = studentTeams;
    }

    public IndividualEvaluationsBuilder(List<StudentTeam> studentTeams, Report report) {
        this(studentTeams);

        this.report = report;
    }

    @Override
    public List<IndividualEvaluation> build() {
        List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

        if (report != null) {
            buildWithReport(individualEvaluations);
        } else {
            buildWithoutReport(individualEvaluations);
        }

        return individualEvaluations;
    }

    private void buildWithReport(List<IndividualEvaluation> individualEvaluations) {
        IndividualEvaluation individualEvaluation;

        for (StudentTeam studentTeam : studentTeams) {
            if (studentTeam.getTeam().getNumber().equalsIgnoreCase(report.getRunningTeam().getTeam().getNumber())) {
                individualEvaluation = new IndividualEvaluation();

                individualEvaluation.setReport(report);
                individualEvaluation.setStudent(studentTeam.getStudent());

                individualEvaluations.add(individualEvaluation);
            }
        }
    }

    private void buildWithoutReport(List<IndividualEvaluation> individualEvaluations) {
        IndividualEvaluation individualEvaluation;

        for (StudentTeam studentTeam : studentTeams) {
            individualEvaluation = new IndividualEvaluation();

            individualEvaluation.setStudent(studentTeam.getStudent());

            individualEvaluations.add(individualEvaluation);
        }
    }
}

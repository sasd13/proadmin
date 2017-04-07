package com.sasd13.proadmin.controller;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IReportController extends IController {

    void newReport(RunningTeam runningTeam);

    void actionNewReport();

    void actionCreateReport(Report report);

    void actionShowReport(Report report);

    void actionUpdateReport(Report report, Report reportToUpdate);

    void createLeadEvaluation(LeadEvaluation leadEvaluation);

    void updateLeadEvaluation(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate);

    void updateIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate);

    void deleteReports(Report[] reports);
}

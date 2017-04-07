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

    void actionNewReport();

    void actionNewReport(RunningTeam runningTeam);

    void actionCreateReport(Report report);

    void actionShowReport(Report report);

    void actionUpdateReport(Report report, Report reportToUpdate);

    void actionDeleteReport(Report report);

    void actionCreateLeadEvaluation(LeadEvaluation leadEvaluation);

    void actionUpdateLeadEvaluation(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate);

    void actionUpdateIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate);
}

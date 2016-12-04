package com.sasd13.proadmin.view.report;

import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.view.IController;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IReportController extends IController {

    void listReports();

    void newReport();

    void newReport(RunningTeam runningTeam);

    void createReport(Report report);

    void showReport(Report runningReport);

    void updateReport(Report report, Report reportToUpdate);

    void updateLeadEvaluation(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate);

    void updateIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate);

    void deleteReport(Report report);
}

package com.sasd13.proadmin.view;

import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IReportController extends IController, IBrowsable {

    void actionNewReport();

    void actionNewReport(RunningTeam runningTeam);

    void actionCreateReport(Report report);

    void actionShowReport(Report report);

    void actionUpdateReport(ReportUpdateWrapper reportUpdateWrapper);

    void actionRemoveReport(Report report);

    void actionCreateLeadEvaluation(LeadEvaluation leadEvaluation);

    void actionUpdateLeadEvaluation(LeadEvaluationUpdateWrapper leadEvaluationUpdateWrapper);

    void actionUpdateIndividualEvaluations(Map<Class, List> allIndividualEvaluations);
}

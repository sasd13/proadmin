package com.sasd13.proadmin.android.view;

import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.update.LeadEvaluationUpdate;
import com.sasd13.proadmin.android.bean.update.ReportUpdate;

import java.util.List;
import java.util.Map;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IReportController extends IController, IBrowsable {

    void actionLoadReports();

    void actionNewReport();

    void actionNewReport(RunningTeam runningTeam);

    void actionCreateReport(Report report);

    void actionShowReport(Report report);

    void actionUpdateReport(ReportUpdate reportUpdate);

    void actionRemoveReport(Report report);

    void actionCreateLeadEvaluation(LeadEvaluation leadEvaluation);

    void actionUpdateLeadEvaluation(LeadEvaluationUpdate leadEvaluationUpdate);

    void actionUpdateIndividualEvaluations(Map<Class, List> allIndividualEvaluations);
}

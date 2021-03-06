package com.sasd13.proadmin.android.component.report.view;

import com.sasd13.proadmin.android.component.IController;
import com.sasd13.proadmin.android.model.IndividualEvaluation;
import com.sasd13.proadmin.android.model.LeadEvaluation;
import com.sasd13.proadmin.android.model.Report;
import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.util.browser.IBrowsable;

import java.util.List;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IReportController extends IController, IBrowsable {

    void actionReadReports();

    void actionNewReport();

    void actionNewReport(RunningTeam runningTeam);

    void actionCreateReport(Report report);

    void actionShowReport(Report report);

    void actionUpdateReport(Report report);

    void actionRemoveReport(Report report);

    void actionSaveLeadEvaluation(LeadEvaluation leadEvaluation);

    void actionSaveIndividualEvaluations(List<IndividualEvaluation> individualEvaluations);
}

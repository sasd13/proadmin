package com.sasd13.proadmin.android.controller.report;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.bean.update.LeadEvaluationUpdate;
import com.sasd13.proadmin.android.bean.update.ReportUpdate;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.ReportScope;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ILeadEvaluationService;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.util.builder.running.NewReportBuilder;
import com.sasd13.proadmin.android.view.IReportController;
import com.sasd13.proadmin.android.view.fragment.report.ReportDetailsFragment;
import com.sasd13.proadmin.android.view.fragment.report.ReportNewFragment;
import com.sasd13.proadmin.android.view.fragment.report.ReportsFragment;
import com.sasd13.proadmin.util.EnumParameter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReportController extends MainController implements IReportController {

    private ReportScope scope;
    private IReportService reportService;
    private ILeadEvaluationService leadEvaluationService;
    private IIndividualEvaluationService individualEvaluationService;
    private IRunningTeamService runningTeamService;
    private ReportReadTask reportReadTask;
    private RunningTeamReadTask runningTeamReadTask;
    private ReportDependenciesTask reportDependenciesTask;
    private ReportCreateTask reportCreateTask;
    private ReportUpdateTask reportUpdateTask;
    private LeadEvaluationCreateTask leadEvaluationCreateTask;
    private LeadEvaluationUpdateTask leadEvaluationUpdateTask;
    private IndividualEvaluationUpdateTask individualEvaluationUpdateTask;
    private ReportDeleteTask reportDeleteTask;

    public ReportController(MainActivity mainActivity, IReportService reportService, ILeadEvaluationService leadEvaluationService, IIndividualEvaluationService individualEvaluationService, IRunningTeamService runningTeamService) {
        super(mainActivity);

        scope = new ReportScope();
        this.reportService = reportService;
        this.leadEvaluationService = leadEvaluationService;
        this.individualEvaluationService = individualEvaluationService;
        this.runningTeamService = runningTeamService;
    }

    @Override
    public Object getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        startFragment(ReportsFragment.newInstance());
        actionLoadReports();
    }

    @Override
    public void actionLoadReports() {
        readReports();
    }

    private void readReports() {
        scope.setLoading(true);

        if (reportReadTask == null) {
            reportReadTask = new ReportReadTask(this, reportService);
        }

        reportReadTask.clearParameters();
        reportReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIntermediary(mainActivity)});
        new Requestor(reportReadTask).execute();
    }

    void onReadReports(List<Report> reports) {
        int index;
        Report report;

        for (Iterator<Report> it = reports.iterator(); it.hasNext(); ) {
            report = it.next();

            if ((index = scope.getReports().indexOf(report)) >= 0) {
                it.remove();
                scope.getReports().set(index, report);
            } else {
                scope.getReports().add(report);
            }
        }

        scope.setReportsToAdd(reports);

        if (!reports.isEmpty()) {
            scope.setReportsToAdd(Collections.<Report>emptyList());
        }

        scope.setLoading(false);
    }

    @Override
    public void actionNewReport() {
        scope.setReport(new NewReportBuilder().build());
        scope.setRunningTeams(new ArrayList<RunningTeam>());
        startFragment(ReportNewFragment.newInstance());
        readRunningTeams();
    }

    private void readRunningTeams() {
        if (runningTeamReadTask == null) {
            runningTeamReadTask = new RunningTeamReadTask(this, runningTeamService);
        }

        runningTeamReadTask.clearParameters();
        runningTeamReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIntermediary(mainActivity)});
        new Requestor(runningTeamReadTask).execute();
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        scope.setRunningTeams(runningTeams);
    }

    @Override
    public void actionNewReport(RunningTeam runningTeam) {
        scope.setReport(new NewReportBuilder(runningTeam).build());
        scope.setRunningTeams(new ArrayList<RunningTeam>());
        startFragment(ReportNewFragment.newInstance());
        readRunningTeams();
    }

    @Override
    public void actionCreateReport(Report report) {
        if (reportCreateTask == null) {
            reportCreateTask = new ReportCreateTask(this, reportService);
        }

        new Requestor(reportCreateTask).execute(report);
    }

    void onCreateReport() {
        display(R.string.message_saved);
        browse();
    }

    @Override
    public void actionShowReport(Report report) {
        scope.setReport(report);
        scope.setStudentTeams(new ArrayList<StudentTeam>());
        scope.setIndividualEvaluations(new ArrayList<IndividualEvaluation>());
        startFragment(ReportDetailsFragment.newInstance());
        readDependencies(report);
    }

    private void readDependencies(Report report) {
        if (reportDependenciesTask == null) {
            reportDependenciesTask = new ReportDependenciesTask(this, reportService);
        }

        reportDependenciesTask.resetParameters();
        reportDependenciesTask.putParameter(IReportService.PARAMATERS_STUDENTTEAM, EnumParameter.TEAM.getName(), new String[]{report.getRunningTeam().getTeam().getNumber()});
        reportDependenciesTask.putParameter(IReportService.PARAMETERS_LEADEVALUATION, EnumParameter.REPORT.getName(), new String[]{report.getNumber()});
        reportDependenciesTask.putParameter(IReportService.PARAMETERS_INDIVIDUALEVALUATION, EnumParameter.REPORT.getName(), new String[]{report.getNumber()});
        new Requestor(reportDependenciesTask).execute();
    }

    void onRetrieved(Map<String, Object> results) {
        scope.setStudentTeams((List<StudentTeam>) results.get(IReportService.PARAMATERS_STUDENTTEAM));

        List<LeadEvaluation> leadEvaluations = (List<LeadEvaluation>) results.get(IReportService.PARAMETERS_LEADEVALUATION);
        if (!leadEvaluations.isEmpty()) {
            scope.setLeadEvaluation(leadEvaluations.get(0));
        }

        scope.setIndividualEvaluations((List<IndividualEvaluation>) results.get(IReportService.PARAMETERS_INDIVIDUALEVALUATION));
    }

    @Override
    public void actionUpdateReport(ReportUpdate reportUpdate) {
        if (reportUpdateTask == null) {
            reportUpdateTask = new ReportUpdateTask(this, reportService);
        }

        new Requestor(reportUpdateTask).execute(reportUpdate);
    }

    void onUpdateReport() {
        display(R.string.message_updated);
    }

    @Override
    public void actionRemoveReport(Report report) {
        if (reportDeleteTask == null) {
            reportDeleteTask = new ReportDeleteTask(this, reportService);
        }

        new Requestor(reportDeleteTask).execute(Arrays.asList(new Report[]{report}));
    }

    void onDeleteReport() {
        display(R.string.message_deleted);
        browse();
    }

    @Override
    public void actionCreateLeadEvaluation(LeadEvaluation leadEvaluation) {
        if (leadEvaluationCreateTask == null) {
            leadEvaluationCreateTask = new LeadEvaluationCreateTask(this, leadEvaluationService);
        }

        new Requestor(leadEvaluationCreateTask).execute(leadEvaluation);
    }

    void onCreateLeadEvaluation() {
        display(R.string.message_saved);
    }

    @Override
    public void actionUpdateLeadEvaluation(LeadEvaluationUpdate leadEvaluationUpdate) {
        if (leadEvaluationUpdateTask == null) {
            leadEvaluationUpdateTask = new LeadEvaluationUpdateTask(this, leadEvaluationService);
        }

        new Requestor(leadEvaluationUpdateTask).execute(leadEvaluationUpdate);
    }

    void onUpdateLeadEvaluation() {
        display(R.string.message_updated);
    }

    @Override
    public void actionUpdateIndividualEvaluations(Map<Class, List> allIndividualEvaluations) {
        if (individualEvaluationUpdateTask == null) {
            individualEvaluationUpdateTask = new IndividualEvaluationUpdateTask(this, individualEvaluationService);
        }

        new Requestor(individualEvaluationUpdateTask).execute(allIndividualEvaluations);
    }

    void onUpdateIndividualEvaluations() {
        display(R.string.message_updated);
    }
}
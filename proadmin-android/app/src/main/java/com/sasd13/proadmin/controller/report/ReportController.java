package com.sasd13.proadmin.controller.report;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.ReportDependencyWrapper;
import com.sasd13.proadmin.view.IReportController;
import com.sasd13.proadmin.view.report.ReportDetailsFragment;
import com.sasd13.proadmin.view.report.ReportNewFragment;
import com.sasd13.proadmin.view.report.ReportsFragment;
import com.sasd13.proadmin.ws.service.IndividualEvaluationService;
import com.sasd13.proadmin.ws.service.LeadEvaluationService;
import com.sasd13.proadmin.ws.service.ReportDependencyService;
import com.sasd13.proadmin.ws.service.ReportService;
import com.sasd13.proadmin.ws.service.RunningTeamService;

import java.util.List;

public class ReportController extends Controller implements IReportController {

    private ReportsFragment reportsFragment;
    private ReportNewFragment reportNewFragment;
    private ReportDetailsFragment reportDetailsFragment;

    private ReportService reportService;
    private LeadEvaluationService leadEvaluationService;
    private IndividualEvaluationService individualEvaluationService;
    private ReportDependencyService dependencyService;
    private RunningTeamService runningTeamService;

    public ReportController(MainActivity mainActivity) {
        super(mainActivity);

        reportService = new ReportService(new ReportServiceCaller(this, mainActivity));
        leadEvaluationService = new LeadEvaluationService(new LeadEvaluationServiceCaller(this, mainActivity));
        individualEvaluationService = new IndividualEvaluationService(new IndividualEvaluationServiceCaller(this, mainActivity));
        dependencyService = new ReportDependencyService(new ReportServiceCaller(this, mainActivity));
        runningTeamService = new RunningTeamService(new RunningTeamServiceCaller(this, mainActivity));
    }

    @Override
    public void entry() {
        listReports();
    }

    @Override
    public void listReports() {
        reportsFragment = ReportsFragment.newInstance(this);

        startFragment(reportsFragment);
        reportService.read(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadReports(List<Report> reports) {
        if (!reportsFragment.isDetached()) {
            reportsFragment.setReports(reports);
        }
    }

    @Override
    public void newReport() {
        reportNewFragment = ReportNewFragment.newInstance(this);

        startFragment(reportNewFragment);
        runningTeamService.read(SessionHelper.getExtraIdTeacherNumber(mainActivity));
        dependencyService.clearParametersStudentTeams();
        dependencyService.read();
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (reportNewFragment != null && !reportNewFragment.isDetached()) {
            reportNewFragment.setRunningTeams(runningTeams);
        }
    }

    void onRetrieved(ReportDependencyWrapper dependencyWrapper) {
        if (reportNewFragment != null && !reportNewFragment.isDetached()) {
            reportNewFragment.setDependencyWrapper(dependencyWrapper);
        }

        if (reportDetailsFragment != null && !reportDetailsFragment.isDetached()) {
            reportDetailsFragment.setDependencyWrapper(dependencyWrapper);
        }
    }

    @Override
    public void newReport(RunningTeam runningTeam) {
        reportNewFragment = ReportNewFragment.newInstance(this);

        startFragment(reportNewFragment);
        runningTeamService.read(SessionHelper.getExtraIdTeacherNumber(mainActivity));
        dependencyService.clearParametersStudentTeams();
        dependencyService.addParameterStudentTeams(EnumParameter.TEAM.getName(), new String[]{runningTeam.getTeam().getNumber()});
        dependencyService.read();
    }

    @Override
    public void createReport(Report report) {
        reportService.create(report);
    }

    @Override
    public void showReport(Report report) {
        reportDetailsFragment = ReportDetailsFragment.newInstance(this, report);

        startFragment(reportDetailsFragment);
    }

    void onReadLeadEvaluation(LeadEvaluation leadEvaluation) {
        if (!reportDetailsFragment.isDetached()) {
            reportDetailsFragment.setLeadEaluation(leadEvaluation);
        }
    }

    void onReadIndividualEvaluations(List<IndividualEvaluation> individualEvaluations) {
        if (!reportDetailsFragment.isDetached()) {
            reportDetailsFragment.setIndividualEvaluations(individualEvaluations);
        }
    }

    @Override
    public void updateReport(Report report, Report reportToUpdate) {
        reportService.update(report, reportToUpdate);
    }

    @Override
    public void updateLeadEvaluation(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        leadEvaluationService.update(leadEvaluation, leadEvaluationToUpdate);
    }

    @Override
    public void updateIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        individualEvaluationService.update(individualEvaluations, individualEvaluationsToUpdate);
    }

    @Override
    public void deleteReport(Report report) {
        reportService.delete(report);
    }
}
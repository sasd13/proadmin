package com.sasd13.proadmin.controller.report;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.fragment.IReportController;
import com.sasd13.proadmin.fragment.report.ReportDetailsFragment;
import com.sasd13.proadmin.fragment.report.ReportNewFragment;
import com.sasd13.proadmin.fragment.report.ReportsFragment;
import com.sasd13.proadmin.service.IndividualEvaluationService;
import com.sasd13.proadmin.service.LeadEvaluationService;
import com.sasd13.proadmin.service.ReportDependencyService;
import com.sasd13.proadmin.service.ReportService;
import com.sasd13.proadmin.service.RunningTeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.Extra;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultReportBuilder;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;
import com.sasd13.proadmin.util.wrapper.ReportsWrapper;

import java.util.List;
import java.util.Map;

public class ReportController extends Controller implements IReportController {

    private ReportService reportService;
    private LeadEvaluationService leadEvaluationService;
    private IndividualEvaluationService individualEvaluationService;
    private ReportDependencyService dependencyService;
    private RunningTeamService runningTeamService;
    private ReportsWrapper reportsWrapper;
    private ReportWrapper reportWrapper;
    private int mode;

    public ReportController(MainActivity mainActivity) {
        super(mainActivity);

        reportService = new ReportService(new ReportServiceCallback(this, mainActivity));
        leadEvaluationService = new LeadEvaluationService(new LeadEvaluationServiceCallback(this, mainActivity));
        individualEvaluationService = new IndividualEvaluationService(new IndividualEvaluationServiceCallback(this, mainActivity));
        dependencyService = new ReportDependencyService(new ReportServiceCallback(this, mainActivity));
        runningTeamService = new RunningTeamService(new RunningTeamServiceCallback(this, mainActivity));
    }

    @Override
    public void entry() {
        mainActivity.clearHistory();
        listReports();
    }

    @Override
    public void readReports() {
        reportService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    @Override
    public void listReports() {
        mode = Extra.MODE_LIST;
        reportsWrapper = new ReportsWrapper();

        startProxyFragment();
        readReports();
    }

    void onReadReports(List<Report> reports) {
        if (isProxyFragmentNotDetached()) {
            reportsWrapper.setReports(reports);
            startFragment(ReportsFragment.newInstance(reportsWrapper));
        }
    }

    @Override
    public void newReport() {
        mode = Extra.MODE_NEW;
        reportWrapper = new ReportWrapper(new DefaultReportBuilder().build());

        startProxyFragment();
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (isProxyFragmentNotDetached()) {
            reportWrapper.setRunningTeams(runningTeams);
            startFragment(ReportNewFragment.newInstance(reportWrapper));

            if (reportWrapper.getReport().getRunningTeam() != null) {
                readStudentTeams(reportWrapper.getReport().getRunningTeam().getTeam());
            }
        }
    }

    @Override
    public void newReport(RunningTeam runningTeam) {
        mode = Extra.MODE_NEW;
        reportWrapper = new ReportWrapper(new DefaultReportBuilder(runningTeam).build());

        startProxyFragment();
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    @Override
    public void readStudentTeams(Team team) {
        dependencyService.clearParametersStudentTeams();
        dependencyService.addParameterStudentTeams(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        dependencyService.read();
    }

    void onRetrieved(Map<String, List> results) {
        reportWrapper.setStudentTeams(results.get(ReportDependencyService.CODE_STUDENTTEAMS));
    }

    @Override
    public void createReport(Report report) {
        reportService.create(report);
    }

    @Override
    public void showReport(Report report) {
        mode = Extra.MODE_EDIT;
        reportWrapper = new ReportWrapper(report);

        startFragment(ReportDetailsFragment.newInstance(reportWrapper));
        readStudentTeams(report.getRunningTeam().getTeam());
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
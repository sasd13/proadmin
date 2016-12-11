package com.sasd13.proadmin.controller.report;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
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
import com.sasd13.proadmin.ws.service.StudentService;

import java.util.List;

public class ReportController extends Controller implements IReportController {

    private ReportNewFragment reportNewFragment;

    private ReportService reportService;
    private LeadEvaluationService leadEvaluationService;
    private IndividualEvaluationService individualEvaluationService;
    private ReportDependencyService dependencyService;
    private RunningTeamService runningTeamService;
    private StudentService studentService;
    private Report report;
    private ReportDependencyWrapper dependencyWrapper;
    private int mode;

    public ReportController(MainActivity mainActivity) {
        super(mainActivity);

        reportService = new ReportService(new ReportServiceCaller(this, mainActivity));
        leadEvaluationService = new LeadEvaluationService(new LeadEvaluationServiceCaller(this, mainActivity));
        individualEvaluationService = new IndividualEvaluationService(new IndividualEvaluationServiceCaller(this, mainActivity));
        dependencyService = new ReportDependencyService(new ReportServiceCaller(this, mainActivity));
        runningTeamService = new RunningTeamService(new RunningTeamServiceCaller(this, mainActivity));
        studentService = new StudentService(new StudentServiceCaller(this, mainActivity));
        dependencyWrapper = new ReportDependencyWrapper();
    }

    @Override
    public void entry() {
        listReports();
    }

    @Override
    public void listReports() {
        mode = Extra.MODE_LIST;

        startProxyFragment();
        reportService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadReports(List<Report> reports) {
        if (isProxyFragmentNotDetached()) {
            if (mode == Extra.MODE_EDIT) {
                studentService.readByTeam(reports.get(0).getRunningTeam().getTeam().getNumber());
            } else {
                startFragment(ReportsFragment.newInstance(this, reports));
            }
        }
    }

    @Override
    public void newReport() {
        reportNewFragment = ReportNewFragment.newInstance(this);

        startFragment(reportNewFragment);
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
        dependencyService.clearParametersStudentTeams();
        dependencyService.read();
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (reportNewFragment != null && !reportNewFragment.isDetached()) {
            reportNewFragment.setRunningTeams(runningTeams);
        }
    }

    void onRetrieved(ReportDependencyService.ResultHolder resultHolder) {
        if (reportNewFragment != null && !reportNewFragment.isDetached()) {
            dependencyWrapper.setStudentTeams(resultHolder.getStudentTeams());
            reportNewFragment.setDependencyWrapper(dependencyWrapper);
        }
    }

    @Override
    public void newReport(RunningTeam runningTeam) {
        reportNewFragment = ReportNewFragment.newInstance(this);

        startFragment(reportNewFragment);
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
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
        mode = Extra.MODE_EDIT;
        this.report = report;

        startProxyFragment();
        reportService.readByNumber(report.getNumber());
    }

    void onReadStudentTeams(List<StudentTeam> studentTeams) {
        if (isProxyFragmentNotDetached()) {
            dependencyWrapper.setStudentTeams(studentTeams);
            startFragment(ReportDetailsFragment.newInstance(this, report, dependencyWrapper));
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
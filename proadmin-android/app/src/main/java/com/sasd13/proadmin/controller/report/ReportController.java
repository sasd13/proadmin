package com.sasd13.proadmin.controller.report;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;
import com.sasd13.proadmin.util.wrapper.ReportsWrapper;
import com.sasd13.proadmin.fragment.IReportController;
import com.sasd13.proadmin.fragment.report.ReportDetailsFragment;
import com.sasd13.proadmin.fragment.report.ReportNewFragment;
import com.sasd13.proadmin.fragment.report.ReportsFragment;
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
    private ReportsWrapper reportsWrapper;
    private ReportWrapper reportWrapper;
    private int mode;

    public ReportController(MainActivity mainActivity) {
        super(mainActivity);

        reportService = new ReportService(new ReportServiceCaller(this, mainActivity));
        leadEvaluationService = new LeadEvaluationService(new LeadEvaluationServiceCaller(this, mainActivity));
        individualEvaluationService = new IndividualEvaluationService(new IndividualEvaluationServiceCaller(this, mainActivity));
        dependencyService = new ReportDependencyService(new ReportServiceCaller(this, mainActivity));
        runningTeamService = new RunningTeamService(new RunningTeamServiceCaller(this, mainActivity));
        studentService = new StudentService(new StudentServiceCaller(this, mainActivity));
    }

    @Override
    public void entry() {
        listReports();
    }

    @Override
    public void listReports() {
        mode = Extra.MODE_LIST;
        reportsWrapper = new ReportsWrapper();

        startProxyFragment();
        reportService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadReports(List<Report> reports) {
        if (isProxyFragmentNotDetached()) {
            reportsWrapper.setReports(reports);

            if (mode == Extra.MODE_EDIT) {
                studentService.readByTeam(reports.get(0).getRunningTeam().getTeam().getNumber());
            } else {
                startFragment(ReportsFragment.newInstance(this, reportsWrapper));
            }
        }
    }

    @Override
    public void newReport() {
        reportNewFragment = ReportNewFragment.newInstance(this);
        reportWrapper = new ReportWrapper();

        startFragment(reportNewFragment);
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (reportNewFragment != null && !reportNewFragment.isDetached()) {
            reportNewFragment.setRunningTeams(runningTeams);
        }
    }

    void onRetrieved(ReportDependencyService.ResultHolder resultHolder) {
        if (reportNewFragment != null && !reportNewFragment.isDetached()) {
            reportWrapper.setStudentTeams(resultHolder.getStudentTeams());
            reportNewFragment.setReportWrapper(reportWrapper);
        }
    }

    @Override
    public void newReport(RunningTeam runningTeam) {
        reportNewFragment = ReportNewFragment.newInstance(this);
        reportWrapper = new ReportWrapper();

        startFragment(reportNewFragment);
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
        readStudentTeams(runningTeam.getTeam());
    }

    @Override
    public void readStudentTeams(Team team) {
        dependencyService.clearParametersStudentTeams();
        dependencyService.addParameterStudentTeams(EnumParameter.TEAM.getName(), new String[]{team.getNumber()});
        dependencyService.read();
    }

    @Override
    public void createReport(Report report) {
        reportService.create(report);
    }

    @Override
    public void showReport(Report report) {
        mode = Extra.MODE_EDIT;
        reportWrapper = new ReportWrapper(report);

        startProxyFragment();
        reportService.readByNumber(report.getNumber());
    }

    void onReadStudentTeams(List<StudentTeam> studentTeams) {
        if (isProxyFragmentNotDetached()) {
            reportWrapper.setStudentTeams(studentTeams);
            startFragment(ReportDetailsFragment.newInstance(this, reportWrapper));
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
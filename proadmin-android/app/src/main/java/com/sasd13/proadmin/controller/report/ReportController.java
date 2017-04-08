package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.ReportScope;
import com.sasd13.proadmin.service.IIndividualEvaluationService;
import com.sasd13.proadmin.service.ILeadEvaluationService;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultReportBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;
import com.sasd13.proadmin.view.IBrowsable;
import com.sasd13.proadmin.view.fragment.report.IReportController;
import com.sasd13.proadmin.view.fragment.report.ReportDetailsFragment;
import com.sasd13.proadmin.view.fragment.report.ReportNewFragment;
import com.sasd13.proadmin.view.fragment.report.ReportsFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportController extends MainController implements IReportController, IBrowsable {

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
        scope.setReports(new ArrayList<Report>());
        startFragment(ReportsFragment.newInstance());
        readReports();
    }

    private void readReports() {
        if (reportReadTask == null) {
            reportReadTask = new ReportReadTask(this, reportService);
        }

        reportReadTask.clearParameters();
        reportReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(reportReadTask).execute();
    }

    void onReadReports(List<Report> reports) {
        for (Report report : reports) {
            if (scope.getReports().contains(report)) {
                reports.remove(report);
            } else {
                scope.getReports().add(report);
            }
        }

        scope.setReportsToAdd(reports);
        scope.setReportsToAdd(Collections.<Report>emptyList());
    }

    @Override
    public void actionNewReport() {
        scope.setRunningTeams(new ArrayList<RunningTeam>());
        scope.setReport(new DefaultReportBuilder().build());
        startFragment(ReportNewFragment.newInstance());
        readRunningTeams();
    }

    private void readRunningTeams() {
        if (runningTeamReadTask == null) {
            runningTeamReadTask = new RunningTeamReadTask(this, runningTeamService);
        }

        runningTeamReadTask.clearParameters();
        runningTeamReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(runningTeamReadTask).execute();
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        scope.setRunningTeams(runningTeams);
    }

    @Override
    public void actionNewReport(RunningTeam runningTeam) {
        scope.setReport(new DefaultReportBuilder(runningTeam).build());
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

    void onRetrieved(Map<String, List> results) {
        scope.setStudentTeams((List<StudentTeam>) results.get(IReportService.PARAMATERS_STUDENTTEAM));

        List<LeadEvaluation> leadEvaluations = (List<LeadEvaluation>) results.get(IReportService.PARAMETERS_LEADEVALUATION);
        if (!leadEvaluations.isEmpty()) {
            scope.setLeadEvaluation(leadEvaluations.get(0));
        }

        scope.setIndividualEvaluations((List<IndividualEvaluation>) results.get(IReportService.PARAMETERS_INDIVIDUALEVALUATION));
    }

    @Override
    public void actionUpdateReport(Report report, Report reportToUpdate) {
        if (reportUpdateTask == null) {
            reportUpdateTask = new ReportUpdateTask(this, reportService);
        }

        new Requestor(reportUpdateTask).execute(getReportUpdateWrapper(report, reportToUpdate));
    }

    private ReportUpdateWrapper getReportUpdateWrapper(Report report, Report reportToUpdate) {
        ReportUpdateWrapper updateWrapper = new ReportUpdateWrapper();

        updateWrapper.setWrapped(report);
        updateWrapper.setNumber(reportToUpdate.getNumber());

        return updateWrapper;
    }

    void onUpdateReport() {
        display(R.string.message_updated);
    }

    @Override
    public void actionRemoveReport(Report report) {
        if (reportDeleteTask == null) {
            reportDeleteTask = new ReportDeleteTask(this, reportService);
        }

        new Requestor(reportDeleteTask).execute(new Report[]{report});
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
    public void actionUpdateLeadEvaluation(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        if (leadEvaluationUpdateTask == null) {
            leadEvaluationUpdateTask = new LeadEvaluationUpdateTask(this, leadEvaluationService);
        }

        new Requestor(leadEvaluationUpdateTask).execute(getLeadEvaluationUpdateWrapper(leadEvaluation, leadEvaluationToUpdate));
    }

    private LeadEvaluationUpdateWrapper getLeadEvaluationUpdateWrapper(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        LeadEvaluationUpdateWrapper updateWrapper = new LeadEvaluationUpdateWrapper();

        updateWrapper.setWrapped(leadEvaluation);
        updateWrapper.setReportNumber(leadEvaluationToUpdate.getReport().getNumber());

        return updateWrapper;
    }

    @Override
    public void actionUpdateIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        if (individualEvaluationUpdateTask == null) {
            individualEvaluationUpdateTask = new IndividualEvaluationUpdateTask(this, individualEvaluationService);
        }

        new Requestor(individualEvaluationUpdateTask).execute(getAllIndividualEvaluations(individualEvaluations, individualEvaluationsToUpdate));
    }

    void onUpdateLeadEvaluation() {
        display(R.string.message_updated);
    }

    private Map<Class, List> getAllIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        Map<Class, List> map = new HashMap<>();
        List<IndividualEvaluation> individualEvaluationsToPost = new ArrayList<>();
        List<IndividualEvaluation> individualEvaluationsToPut = new ArrayList<>();

        boolean toPost;

        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            toPost = true;

            for (IndividualEvaluation individualEvaluationToUpdate : individualEvaluationsToUpdate) {
                if (individualEvaluationToUpdate.getStudent().getNumber().equals(individualEvaluation.getStudent().getNumber())) {
                    toPost = false;

                    break;
                }
            }

            if (toPost) {
                individualEvaluationsToPost.add(individualEvaluation);
            } else {
                individualEvaluationsToPut.add(individualEvaluation);
            }
        }

        map.put(IndividualEvaluation.class, individualEvaluationsToPost);
        map.put(IndividualEvaluationUpdateWrapper.class, getIndividualEvaluationUpdateWrapper(individualEvaluationsToPut, individualEvaluationsToUpdate));

        return map;
    }

    private List<IndividualEvaluationUpdateWrapper> getIndividualEvaluationUpdateWrapper(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        List<IndividualEvaluationUpdateWrapper> updateWrappers = new ArrayList<>();

        IndividualEvaluationUpdateWrapper updateWrapper;

        for (IndividualEvaluation individualEvaluationToUpdate : individualEvaluationsToUpdate) {
            updateWrapper = new IndividualEvaluationUpdateWrapper();

            for (IndividualEvaluation individualEvaluation : individualEvaluations) {
                if (individualEvaluation.getStudent().getNumber().equals(individualEvaluationToUpdate.getStudent().getNumber())) {
                    individualEvaluation.setReport(individualEvaluationToUpdate.getReport());
                    updateWrapper.setWrapped(individualEvaluation);
                    updateWrapper.setReportNumber(individualEvaluationToUpdate.getReport().getNumber());
                    updateWrapper.setStudentNumber(individualEvaluationToUpdate.getStudent().getNumber());

                    break;
                }
            }

            updateWrappers.add(updateWrapper);
        }

        return updateWrappers;
    }

    void onUpdateIndividualEvaluations() {
        display(R.string.message_updated);
    }
}
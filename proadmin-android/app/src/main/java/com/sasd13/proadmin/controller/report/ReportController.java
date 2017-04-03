package com.sasd13.proadmin.controller.report;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.IReportController;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.view.fragment.report.ReportDetailsFragment;
import com.sasd13.proadmin.view.fragment.report.ReportNewFragment;
import com.sasd13.proadmin.view.fragment.report.ReportsFragment;
import com.sasd13.proadmin.service.IIndividualEvaluationService;
import com.sasd13.proadmin.service.ILeadEvaluationService;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultReportBuilder;
import com.sasd13.proadmin.util.wrapper.ReportWrapper;
import com.sasd13.proadmin.util.wrapper.ReportsWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.IndividualEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.LeadEvaluationUpdateWrapper;
import com.sasd13.proadmin.util.wrapper.update.running.ReportUpdateWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportController extends MainController implements IReportController {

    private IReportService reportService;
    private ILeadEvaluationService leadEvaluationService;
    private IIndividualEvaluationService individualEvaluationService;
    private IRunningTeamService runningTeamService;
    private ReportReadStrategy reportReadStrategy;
    private RunningTeamReadStrategy runningTeamReadStrategy;
    private ReportDependenciesStrategy reportDependenciesStrategy;
    private ReportUpdateStrategy reportUpdateStrategy;
    private LeadEvaluationCreateStrategy leadEvaluationCreateStrategy;
    private LeadEvaluationUpdateStrategy leadEvaluationUpdateStrategy;
    private IndividualEvaluationUpdateStrategy individualEvaluationUpdateStrategy;
    private ReportDeleteStrategy reportDeleteStrategy;
    private ReportsWrapper reportsWrapper;
    private ReportWrapper reportWrapper;

    public ReportController(MainActivity mainActivity, IReportService reportService, ILeadEvaluationService leadEvaluationService, IIndividualEvaluationService individualEvaluationService, IRunningTeamService runningTeamService) {
        super(mainActivity);

        this.reportService = reportService;
        this.leadEvaluationService = leadEvaluationService;
        this.individualEvaluationService = individualEvaluationService;
        this.runningTeamService = runningTeamService;
    }

    @Override
    public void entry() {
        mainActivity.clearHistory();
        listReports();
    }

    @Override
    public void listReports() {
        reportsWrapper = new ReportsWrapper();

        startProxyFragment();
        readReports();
    }

    private void readReports() {
        if (reportReadStrategy == null) {
            reportReadStrategy = new ReportReadStrategy(this, reportService);
        }

        reportReadStrategy.clearParameters();
        reportReadStrategy.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(reportReadStrategy).execute();
    }

    void onReadReports(List<Report> reports) {
        if (isProxyFragmentNotDetached()) {
            reportsWrapper.setReports(reports);
            startFragment(ReportsFragment.newInstance(reportsWrapper));
        }
    }

    @Override
    public void newReport() {
        reportWrapper = new ReportWrapper(new DefaultReportBuilder().build());

        startProxyFragment();
        readRunningTeams();
    }

    private void readRunningTeams() {
        if (runningTeamReadStrategy == null) {
            runningTeamReadStrategy = new RunningTeamReadStrategy(this, runningTeamService);
        }

        runningTeamReadStrategy.clearParameters();
        runningTeamReadStrategy.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        new Requestor(runningTeamReadStrategy).execute();
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (isProxyFragmentNotDetached()) {
            reportWrapper.setRunningTeams(runningTeams);
            startFragment(ReportNewFragment.newInstance(reportWrapper));
        }
    }

    @Override
    public void newReport(RunningTeam runningTeam) {
        reportWrapper = new ReportWrapper(new DefaultReportBuilder(runningTeam).build());

        startProxyFragment();
        readRunningTeams();
    }

    @Override
    public void createReport(Report report) {
        reportService.create(report);
    }

    @Override
    public void showReport(Report report) {
        reportWrapper = new ReportWrapper(report);

        startFragment(ReportDetailsFragment.newInstance(reportWrapper));
        readDependencies(report);
    }

    private void readDependencies(Report report) {
        if (reportDependenciesStrategy == null) {
            reportDependenciesStrategy = new ReportDependenciesStrategy(this, reportService);
        }

        reportDependenciesStrategy.resetParameters();
        reportDependenciesStrategy.putParameter(IReportService.PARAMATERS_STUDENTTEAM, EnumParameter.TEAM.getName(), new String[]{report.getRunningTeam().getTeam().getNumber()});
        reportDependenciesStrategy.putParameter(IReportService.PARAMETERS_LEADEVALUATION, EnumParameter.REPORT.getName(), new String[]{report.getNumber()});
        reportDependenciesStrategy.putParameter(IReportService.PARAMETERS_INDIVIDUALEVALUATION, EnumParameter.REPORT.getName(), new String[]{report.getNumber()});
        new Requestor(reportDependenciesStrategy).execute();
    }

    void onRetrieved(Map<String, List> results) {
        reportWrapper.setStudentTeams((List<StudentTeam>) results.get(IReportService.PARAMATERS_STUDENTTEAM));

        List<LeadEvaluation> leadEvaluations = (List<LeadEvaluation>) results.get(IReportService.PARAMETERS_LEADEVALUATION);
        if (!leadEvaluations.isEmpty()) {
            reportWrapper.setLeadEvaluation(leadEvaluations.get(0));
        }

        reportWrapper.setIndividualEvaluations((List<IndividualEvaluation>) results.get(IReportService.PARAMETERS_INDIVIDUALEVALUATION));
    }

    @Override
    public void updateReport(Report report, Report reportToUpdate) {
        if (reportUpdateStrategy == null) {
            reportUpdateStrategy = new ReportUpdateStrategy(this, reportService);
        }

        new Requestor(reportUpdateStrategy).execute(getReportUpdateWrapper(report, reportToUpdate));
    }

    private ReportUpdateWrapper getReportUpdateWrapper(Report report, Report reportToUpdate) {
        ReportUpdateWrapper updateWrapper = new ReportUpdateWrapper();

        updateWrapper.setWrapped(report);
        updateWrapper.setNumber(reportToUpdate.getNumber());

        return updateWrapper;
    }

    @Override
    public void createLeadEvaluation(LeadEvaluation leadEvaluation) {
        if (leadEvaluationCreateStrategy == null) {
            leadEvaluationCreateStrategy = new LeadEvaluationCreateStrategy(this, leadEvaluationService);
        }

        new Requestor(leadEvaluationCreateStrategy).execute(leadEvaluation);
    }

    @Override
    public void updateLeadEvaluation(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        if (leadEvaluationUpdateStrategy == null) {
            leadEvaluationUpdateStrategy = new LeadEvaluationUpdateStrategy(this, leadEvaluationService);
        }

        new Requestor(leadEvaluationUpdateStrategy).execute(getLeadEvaluationUpdateWrapper(leadEvaluation, leadEvaluationToUpdate));
    }

    private LeadEvaluationUpdateWrapper getLeadEvaluationUpdateWrapper(LeadEvaluation leadEvaluation, LeadEvaluation leadEvaluationToUpdate) {
        LeadEvaluationUpdateWrapper updateWrapper = new LeadEvaluationUpdateWrapper();

        updateWrapper.setWrapped(leadEvaluation);
        updateWrapper.setReportNumber(leadEvaluationToUpdate.getReport().getNumber());

        return updateWrapper;
    }

    @Override
    public void updateIndividualEvaluations(List<IndividualEvaluation> individualEvaluations, List<IndividualEvaluation> individualEvaluationsToUpdate) {
        if (individualEvaluationUpdateStrategy == null) {
            individualEvaluationUpdateStrategy = new IndividualEvaluationUpdateStrategy(this, individualEvaluationService);
        }

        new Requestor(individualEvaluationUpdateStrategy).execute(getAllIndividualEvaluations(individualEvaluations, individualEvaluationsToUpdate));
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
                }
            }

            updateWrappers.add(updateWrapper);
        }

        return updateWrappers;
    }

    @Override
    public void deleteReports(Report[] reports) {
        if (reportDeleteStrategy == null) {
            reportDeleteStrategy = new ReportDeleteStrategy(this, reportService);
        }

        new Requestor(reportDeleteStrategy).execute(reports);
    }
}
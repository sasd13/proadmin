package com.sasd13.proadmin.android.controller.report;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.IndividualEvaluation;
import com.sasd13.proadmin.android.bean.LeadEvaluation;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.StudentTeam;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.ReportScope;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IIndividualEvaluationService;
import com.sasd13.proadmin.android.service.ILeadEvaluationService;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.service.ISessionStorageService;
import com.sasd13.proadmin.android.service.IUserStorageService;
import com.sasd13.proadmin.android.util.builder.NewIndividualEvaluationBuilder;
import com.sasd13.proadmin.android.util.builder.NewLeadEvaluationBuilder;
import com.sasd13.proadmin.android.util.builder.NewReportBuilder;
import com.sasd13.proadmin.android.util.wrapper.IndividualEvaluationSaveWrapper;
import com.sasd13.proadmin.android.view.IReportController;
import com.sasd13.proadmin.android.view.fragment.report.ReportDetailsFragment;
import com.sasd13.proadmin.android.view.fragment.report.ReportNewFragment;
import com.sasd13.proadmin.android.view.fragment.report.ReportsFragment;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.util.EnumRestriction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReportController extends MainController implements IReportController {

    private ReportScope scope;
    private ISessionStorageService sessionStorageService;
    private IUserStorageService userStorageService;
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
    private IndividualEvaluationSaveTask individualEvaluationSaveTask;
    private ReportDeleteTask reportDeleteTask;
    private Map<String, String[]> reportCriterias, runningTeamCriterias, studentTeamCriterias, leadEvaluationCriterias, individualEvaluationCriterias;

    public ReportController(MainActivity mainActivity, ISessionStorageService sessionStorageService, IUserStorageService userStorageService, IReportService reportService, ILeadEvaluationService leadEvaluationService, IIndividualEvaluationService individualEvaluationService, IRunningTeamService runningTeamService) {
        super(mainActivity);

        scope = new ReportScope();
        this.sessionStorageService = sessionStorageService;
        this.userStorageService = userStorageService;
        this.reportService = reportService;
        this.leadEvaluationService = leadEvaluationService;
        this.individualEvaluationService = individualEvaluationService;
        this.runningTeamService = runningTeamService;
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        getActivity().clearHistory();
        scope.setUserPreferences(userStorageService.read().getUserPreferences());
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
            reportCriterias = new HashMap<>();
        } else {
            reportCriterias.clear();
        }

        Map<String, Object> allCriterias = new HashMap<>();

        reportCriterias.put(EnumCriteria.TEACHER.getCode(), new String[]{sessionStorageService.getIntermediary()});
        allCriterias.put(EnumRestriction.WHERE.getCode(), reportCriterias);

        new Requestor(reportReadTask).execute(allCriterias);
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
            scope.clearReportsToAdd();
        }

        scope.setLoading(false);
    }

    @Override
    public void actionNewReport() {
        scope.setReport(new NewReportBuilder().build());
        scope.setRunningTeams(new ArrayList<RunningTeam>());
        startFragment(ReportNewFragment.newInstance());
        readRunningTeams(Calendar.getInstance().get(Calendar.YEAR));
    }

    private void readRunningTeams(int year) {
        if (runningTeamReadTask == null) {
            runningTeamReadTask = new RunningTeamReadTask(this, runningTeamService);
            runningTeamCriterias = new HashMap<>();
        } else {
            runningTeamCriterias.clear();
        }

        Map<String, Object> allCriterias = new HashMap<>();

        runningTeamCriterias.put(EnumCriteria.YEAR.getCode(), new String[]{String.valueOf(year)});
        runningTeamCriterias.put(EnumCriteria.TEACHER.getCode(), new String[]{sessionStorageService.getIntermediary()});
        allCriterias.put(EnumRestriction.WHERE.getCode(), runningTeamCriterias);

        new Requestor(runningTeamReadTask).execute(allCriterias);
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        scope.setRunningTeams(runningTeams);
    }

    @Override
    public void actionNewReport(RunningTeam runningTeam) {
        scope.setReport(new NewReportBuilder(runningTeam).build());
        scope.setRunningTeams(new ArrayList<RunningTeam>());
        startFragment(ReportNewFragment.newInstance());
        readRunningTeams(runningTeam.getRunning().getYear());
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
        scope.setLeadEvaluation(new NewLeadEvaluationBuilder(report).build());
        scope.setIndividualEvaluations(new ArrayList<IndividualEvaluation>());
        startFragment(ReportDetailsFragment.newInstance());
        readDependencies(report);
    }

    private void readDependencies(Report report) {
        if (reportDependenciesTask == null) {
            reportDependenciesTask = new ReportDependenciesTask(this, reportService);
            studentTeamCriterias = new HashMap<>();
            leadEvaluationCriterias = new HashMap<>();
            individualEvaluationCriterias = new HashMap<>();
        } else {
            studentTeamCriterias.clear();
            leadEvaluationCriterias.clear();
            individualEvaluationCriterias.clear();
        }

        Map<String, Object> allCriterias = new HashMap<>();
        Map<String, Object> allStudentTeamCriterias = new HashMap<>();
        Map<String, Object> allLeadEvaluationCriterias = new HashMap<>();
        Map<String, Object> allIndividualEvaluationCriterias = new HashMap<>();

        studentTeamCriterias.put(EnumCriteria.TEAM.getCode(), new String[]{report.getRunningTeam().getTeam().getNumber()});
        allStudentTeamCriterias.put(EnumRestriction.WHERE.getCode(), studentTeamCriterias);
        allCriterias.put(IReportService.PARAMATERS_STUDENTTEAM, allStudentTeamCriterias);

        leadEvaluationCriterias.put(EnumCriteria.REPORT.getCode(), new String[]{report.getNumber()});
        allLeadEvaluationCriterias.put(EnumRestriction.WHERE.getCode(), leadEvaluationCriterias);
        allCriterias.put(IReportService.PARAMETERS_LEADEVALUATION, allLeadEvaluationCriterias);

        individualEvaluationCriterias.put(EnumCriteria.REPORT.getCode(), new String[]{report.getNumber()});
        allIndividualEvaluationCriterias.put(EnumRestriction.WHERE.getCode(), individualEvaluationCriterias);
        allCriterias.put(IReportService.PARAMETERS_INDIVIDUALEVALUATION, allIndividualEvaluationCriterias);

        new Requestor(reportDependenciesTask).execute(allCriterias);
    }

    void onRetrieved(Map<String, Object> results) {
        List<StudentTeam> studentTeams = (List<StudentTeam>) results.get(IReportService.PARAMATERS_STUDENTTEAM);
        List<LeadEvaluation> leadEvaluations = (List<LeadEvaluation>) results.get(IReportService.PARAMETERS_LEADEVALUATION);
        List<IndividualEvaluation> individualEvaluations = (List<IndividualEvaluation>) results.get(IReportService.PARAMETERS_INDIVIDUALEVALUATION);

        boolean contains;

        for (StudentTeam studentTeam : studentTeams) {
            contains = false;

            for (IndividualEvaluation individualEvaluation : individualEvaluations) {
                if (individualEvaluation.getStudent().equals(studentTeam.getStudent())) {
                    contains = true;
                }
            }

            if (!contains) {
                individualEvaluations.add(new NewIndividualEvaluationBuilder(scope.getReport(), studentTeam.getStudent()).build());
            }
        }

        scope.setStudentTeams(studentTeams);

        if (!leadEvaluations.isEmpty()) {
            scope.setLeadEvaluation(leadEvaluations.get(0));
        }

        scope.setIndividualEvaluations(individualEvaluations);
    }

    @Override
    public void actionUpdateReport(Report report) {
        if (reportUpdateTask == null) {
            reportUpdateTask = new ReportUpdateTask(this, reportService);
        }

        new Requestor(reportUpdateTask).execute(report);
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
    public void actionSaveLeadEvaluation(LeadEvaluation leadEvaluation) {
        if (leadEvaluation.getId() > 0) {
            updateLeadEvaluation(leadEvaluation);
        } else {
            createLeadEvaluation(leadEvaluation);
        }
    }

    private void createLeadEvaluation(LeadEvaluation leadEvaluation) {
        if (leadEvaluationCreateTask == null) {
            leadEvaluationCreateTask = new LeadEvaluationCreateTask(this, leadEvaluationService);
        }

        new Requestor(leadEvaluationCreateTask).execute(leadEvaluation);
    }

    void onCreateLeadEvaluation() {
        display(R.string.message_saved);
    }

    private void updateLeadEvaluation(LeadEvaluation leadEvaluation) {
        if (leadEvaluationUpdateTask == null) {
            leadEvaluationUpdateTask = new LeadEvaluationUpdateTask(this, leadEvaluationService);
        }

        new Requestor(leadEvaluationUpdateTask).execute(leadEvaluation);
    }

    void onUpdateLeadEvaluation() {
        display(R.string.message_updated);
    }

    @Override
    public void actionSaveIndividualEvaluations(List<IndividualEvaluation> individualEvaluations) {
        if (individualEvaluationSaveTask == null) {
            individualEvaluationSaveTask = new IndividualEvaluationSaveTask(this, individualEvaluationService);
        }

        IndividualEvaluationSaveWrapper wrapper = new IndividualEvaluationSaveWrapper();
        List<IndividualEvaluation> individualEvaluationsToUpdate = new ArrayList<>();
        List<IndividualEvaluation> individualEvaluationsToCreate = new ArrayList<>();

        for (IndividualEvaluation individualEvaluation : individualEvaluations) {
            if (individualEvaluation.getId() > 0) {
                individualEvaluationsToUpdate.add(individualEvaluation);
            } else {
                individualEvaluationsToCreate.add(individualEvaluation);
            }
        }

        wrapper.setIndividualEvaluationsToUpdate(individualEvaluationsToUpdate);
        wrapper.setIndividualEvaluationsToCreate(individualEvaluationsToCreate);

        new Requestor(individualEvaluationSaveTask).execute(wrapper);
    }

    void onUpdateIndividualEvaluations() {
        display(R.string.message_updated);
    }
}
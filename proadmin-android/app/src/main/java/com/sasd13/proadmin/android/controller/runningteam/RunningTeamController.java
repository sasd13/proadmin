package com.sasd13.proadmin.android.controller.runningteam;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.controller.MainController;
import com.sasd13.proadmin.android.scope.RunningTeamScope;
import com.sasd13.proadmin.android.scope.Scope;
import com.sasd13.proadmin.android.service.IReportService;
import com.sasd13.proadmin.android.service.IRunningTeamService;
import com.sasd13.proadmin.android.util.SessionHelper;
import com.sasd13.proadmin.android.util.builder.running.NewRunningTeamBuilder;
import com.sasd13.proadmin.android.view.IRunningTeamController;
import com.sasd13.proadmin.android.view.fragment.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.android.view.fragment.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.android.view.fragment.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.util.EnumRestriction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RunningTeamController extends MainController implements IRunningTeamController {

    private RunningTeamScope scope;
    private IRunningTeamService runningTeamService;
    private IReportService reportService;
    private RunningTeamReadTask runningTeamReadTask;
    private RunningTeamDependenciesTask runningTeamDependenciesTask;
    private ReportReadTask reportReadTask;
    private RunningTeamCreateTask runningTeamCreateTask;
    private RunningTeamUpdateTask runningTeamUpdateTask;
    private RunningTeamDeleteTask runningTeamDeleteTask;
    private Map<String, Object> allCriterias;
    private Map<String, String[]> runningTeamCriterias, runningCriterias, reportCriterias;

    public RunningTeamController(MainActivity mainActivity, IRunningTeamService runningTeamService, IReportService reportService) {
        super(mainActivity);

        scope = new RunningTeamScope();
        this.runningTeamService = runningTeamService;
        this.reportService = reportService;
        allCriterias = new HashMap<>();
    }

    @Override
    public Scope getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        startFragment(RunningTeamsFragment.newInstance());
        actionLoadRunningTeams();
    }

    @Override
    public void actionLoadRunningTeams() {
        readRunningTeams();
    }

    private void readRunningTeams() {
        scope.setLoading(true);

        if (runningTeamReadTask == null) {
            runningTeamReadTask = new RunningTeamReadTask(this, runningTeamService);
            runningTeamCriterias = new HashMap<>();
        } else {
            runningTeamCriterias.clear();
        }

        allCriterias.clear();
        runningTeamCriterias.put(EnumCriteria.TEACHER.getCode(), new String[]{SessionHelper.getExtraIntermediary(mainActivity)});
        allCriterias.put(EnumRestriction.WHERE.getCode(), runningTeamCriterias);

        new Requestor(runningTeamReadTask).execute(allCriterias);
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        int index;
        RunningTeam runningTeam;

        for (Iterator<RunningTeam> it = runningTeams.iterator(); it.hasNext(); ) {
            runningTeam = it.next();

            if ((index = scope.getRunningTeams().indexOf(runningTeam)) >= 0) {
                it.remove();
                scope.getRunningTeams().set(index, runningTeam);
            } else {
                scope.getRunningTeams().add(runningTeam);
            }
        }

        scope.setRunningTeamsToAdd(runningTeams);

        if (!runningTeams.isEmpty()) {
            scope.clearRunningTeamsToAdd();
        }

        scope.setLoading(false);
    }

    @Override
    public void actionNewRunningTeam() {
        scope.setRunningTeam(new NewRunningTeamBuilder().build());
        resetDependencies();
        startFragment(RunningTeamNewFragment.newInstance());
        readDependencies();
    }

    private void resetDependencies() {
        scope.setRunnings(new ArrayList<Running>());
        scope.setTeams(new ArrayList<Team>());
        scope.setAcademicLevels(new ArrayList<AcademicLevel>());
    }

    private void readDependencies() {
        if (runningTeamDependenciesTask == null) {
            runningTeamDependenciesTask = new RunningTeamDependenciesTask(this, runningTeamService);
            runningCriterias = new HashMap<>();
        } else {
            runningCriterias.clear();
        }

        allCriterias.clear();
        runningCriterias.put(EnumCriteria.TEACHER.getCode(), new String[]{SessionHelper.getExtraIntermediary(mainActivity)});
        allCriterias.put(IRunningTeamService.PARAMATERS_RUNNING, runningCriterias);

        new Requestor(runningTeamDependenciesTask).execute(allCriterias);
    }

    void onRetrieved(Map<String, Object> results) {
        scope.setRunnings((List<Running>) results.get(IRunningTeamService.PARAMATERS_RUNNING));
        scope.setTeams((List<Team>) results.get(IRunningTeamService.PARAMETERS_TEAM));
        scope.setAcademicLevels((List<AcademicLevel>) results.get(IRunningTeamService.PARAMETERS_ACADEMICLEVEL));
    }

    @Override
    public void actionCreateRunningTeam(RunningTeam runningTeam) {
        if (runningTeamCreateTask == null) {
            runningTeamCreateTask = new RunningTeamCreateTask(this, runningTeamService);
        }

        new Requestor(runningTeamCreateTask).execute(runningTeam);
    }

    void onCreateRunningTeam() {
        display(R.string.message_saved);
        browse();
    }

    @Override
    public void actionShowRunningTeam(RunningTeam runningTeam) {
        scope.setRunningTeam(runningTeam);
        resetDependencies();
        scope.setReports(new ArrayList<Report>());
        startFragment(RunningTeamDetailsFragment.newInstance());
        readDependencies();
        readReports(runningTeam);
    }

    private void readReports(RunningTeam runningTeam) {
        if (reportReadTask == null) {
            reportReadTask = new ReportReadTask(this, reportService);
            reportCriterias = new HashMap<>();
        } else {
            reportCriterias.clear();
        }

        allCriterias.clear();
        reportCriterias.put(EnumCriteria.YEAR.getCode(), new String[]{String.valueOf(runningTeam.getRunning().getYear())});
        reportCriterias.put(EnumCriteria.PROJECT.getCode(), new String[]{runningTeam.getRunning().getProject().getCode()});
        reportCriterias.put(EnumCriteria.TEACHER.getCode(), new String[]{runningTeam.getRunning().getTeacher().getIntermediary()});
        reportCriterias.put(EnumCriteria.TEAM.getCode(), new String[]{runningTeam.getTeam().getNumber()});
        reportCriterias.put(EnumCriteria.ACADEMICLEVEL.getCode(), new String[]{runningTeam.getAcademicLevel().getCode()});
        allCriterias.put(EnumRestriction.WHERE.getCode(), reportCriterias);

        new Requestor(reportReadTask).execute(allCriterias);
    }

    void onReadReports(List<Report> reports) {
        scope.setReports(reports);
    }

    @Override
    public void actionUpdateRunningTeam(RunningTeam runningTeam) {
        if (runningTeamUpdateTask == null) {
            runningTeamUpdateTask = new RunningTeamUpdateTask(this, runningTeamService);
        }

        new Requestor(runningTeamUpdateTask).execute(runningTeam);
    }

    void onUpdateRunningTeam() {
        display(R.string.message_updated);
    }

    @Override
    public void actionRemoveRunningTeam(RunningTeam runningTeam) {
        if (runningTeamDeleteTask == null) {
            runningTeamDeleteTask = new RunningTeamDeleteTask(this, runningTeamService);
        }

        new Requestor(runningTeamDeleteTask).execute(Arrays.asList(new RunningTeam[]{runningTeam}));
    }

    void onDeleteRunningTeam() {
        display(R.string.message_deleted);
        browse();
    }
}
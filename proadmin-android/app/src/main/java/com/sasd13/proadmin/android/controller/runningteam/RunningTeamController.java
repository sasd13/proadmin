package com.sasd13.proadmin.android.controller.runningteam;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.android.R;
import com.sasd13.proadmin.android.activity.MainActivity;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Report;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Team;
import com.sasd13.proadmin.android.bean.update.RunningTeamUpdate;
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
import com.sasd13.proadmin.util.EnumParameter;

import java.util.ArrayList;
import java.util.Arrays;
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

    public RunningTeamController(MainActivity mainActivity, IRunningTeamService runningTeamService, IReportService reportService) {
        super(mainActivity);

        scope = new RunningTeamScope();
        this.runningTeamService = runningTeamService;
        this.reportService = reportService;
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
        }

        runningTeamReadTask.clearParameters();
        runningTeamReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIntermediary(mainActivity)});
        new Requestor(runningTeamReadTask).execute();
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
        }

        runningTeamDependenciesTask.resetParameters();
        runningTeamDependenciesTask.putParameter(IRunningTeamService.PARAMATERS_RUNNING, EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIntermediary(mainActivity)});
        new Requestor(runningTeamDependenciesTask).execute();
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
        }

        reportReadTask.clearParameters();
        reportReadTask.putParameter(EnumParameter.YEAR.getName(), new String[]{String.valueOf(runningTeam.getRunning().getYear())});
        reportReadTask.putParameter(EnumParameter.PROJECT.getName(), new String[]{runningTeam.getRunning().getProject().getCode()});
        reportReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{runningTeam.getRunning().getTeacher().getIntermediary()});
        reportReadTask.putParameter(EnumParameter.TEAM.getName(), new String[]{runningTeam.getTeam().getNumber()});
        reportReadTask.putParameter(EnumParameter.ACADEMICLEVEL.getName(), new String[]{runningTeam.getAcademicLevel().getCode()});
        new Requestor(reportReadTask).execute();
    }

    void onReadReports(List<Report> reports) {
        scope.setReports(reports);
    }

    @Override
    public void actionUpdateRunningTeam(RunningTeamUpdate runningTeamUpdate) {
        if (runningTeamUpdateTask == null) {
            runningTeamUpdateTask = new RunningTeamUpdateTask(this, runningTeamService);
        }

        new Requestor(runningTeamUpdateTask).execute(runningTeamUpdate);
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
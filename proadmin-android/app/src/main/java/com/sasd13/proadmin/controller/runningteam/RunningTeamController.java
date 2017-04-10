package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.androidex.util.requestor.Requestor;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.MainController;
import com.sasd13.proadmin.scope.RunningTeamScope;
import com.sasd13.proadmin.service.IReportService;
import com.sasd13.proadmin.service.IRunningTeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningTeamBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;
import com.sasd13.proadmin.view.IRunningTeamController;
import com.sasd13.proadmin.view.fragment.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.view.fragment.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.view.fragment.runningteam.RunningTeamsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    public Object getScope() {
        return scope;
    }

    @Override
    public void browse() {
        mainActivity.clearHistory();
        scope.setRunningTeams(new ArrayList<RunningTeam>());
        startFragment(RunningTeamsFragment.newInstance());
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
        int index;

        for (RunningTeam runningTeam : runningTeams) {
            if ((index = scope.getRunningTeams().indexOf(runningTeam)) >= 0) {
                runningTeams.remove(runningTeam);
                scope.getRunningTeams().set(index, runningTeam);
            } else {
                scope.getRunningTeams().add(runningTeam);
            }
        }

        scope.setRunningTeamsToAdd(runningTeams);
        scope.setRunningTeamsToAdd(Collections.<RunningTeam>emptyList());
    }

    @Override
    public void actionNewRunningTeam() {
        scope.setRunningTeam(new DefaultRunningTeamBuilder().build());
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
        runningTeamDependenciesTask.putParameter(IRunningTeamService.PARAMATERS_RUNNING, EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        runningTeamDependenciesTask.execute();
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
        reportReadTask.putParameter(EnumParameter.TEACHER.getName(), new String[]{runningTeam.getRunning().getTeacher().getNumber()});
        reportReadTask.putParameter(EnumParameter.TEAM.getName(), new String[]{runningTeam.getTeam().getNumber()});
        reportReadTask.putParameter(EnumParameter.ACADEMICLEVEL.getName(), new String[]{runningTeam.getAcademicLevel().getCode()});
        new Requestor(reportReadTask).execute();
    }

    void onReadReports(List<Report> reports) {
        scope.setReports(reports);
    }

    @Override
    public void actionUpdateRunningTeam(RunningTeamUpdateWrapper runningTeamUpdateWrapper) {
        if (runningTeamUpdateTask == null) {
            runningTeamUpdateTask = new RunningTeamUpdateTask(this, runningTeamService);
        }

        new Requestor(runningTeamUpdateTask).execute(runningTeamUpdateWrapper);
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
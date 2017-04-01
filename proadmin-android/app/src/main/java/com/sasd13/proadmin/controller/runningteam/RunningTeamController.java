package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.fragment.IRunningTeamController;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.service.impl.ReportService;
import com.sasd13.proadmin.service.impl.RunningTeamService;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.Extra;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningTeamBuilder;
import com.sasd13.proadmin.util.wrapper.RunningTeamWrapper;
import com.sasd13.proadmin.util.wrapper.RunningTeamsWrapper;

import java.util.List;
import java.util.Map;

public class RunningTeamController extends Controller implements IRunningTeamController {

    private RunningTeamService runningTeamService;
    private RunningTeamDependencyService runningTeamDependencyService;
    private ReportService reportService;
    private RunningTeamsWrapper runningTeamsWrapper;
    private RunningTeamWrapper runningTeamWrapper;
    private int mode;

    public RunningTeamController(MainActivity mainActivity) {
        super(mainActivity);

        runningTeamService = new RunningTeamService(new RunningTeamServiceCallback(this, mainActivity));
        runningTeamDependencyService = new RunningTeamDependencyService(new RunningTeamServiceCallback(this, mainActivity));
        reportService = new ReportService(new ReportServiceCallback(this, mainActivity));
    }

    @Override
    public void entry() {
        mainActivity.clearHistory();
        listRunningTeams();
    }

    @Override
    public void readRunningTeams() {
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    @Override
    public void listRunningTeams() {
        runningTeamsWrapper = new RunningTeamsWrapper();

        startProxyFragment();
        readRunningTeams();
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (isProxyFragmentNotDetached()) {
            runningTeamsWrapper.setRunningTeams(runningTeams);
            startFragment(RunningTeamsFragment.newInstance(runningTeamsWrapper));
        }
    }

    @Override
    public void newRunningTeam() {
        mode = Extra.MODE_NEW;
        runningTeamWrapper = new RunningTeamWrapper(new DefaultRunningTeamBuilder().build());

        startProxyFragment();
        readDependencies();
    }

    private void readDependencies() {
        runningTeamDependencyService.clearParameters();
        runningTeamDependencyService.addParameterRunnings(EnumParameter.TEACHER.getName(), new String[]{SessionHelper.getExtraIdTeacherNumber(mainActivity)});
        runningTeamDependencyService.read();
    }

    void onRetrieved(Map<String, List> results) {
        if (isProxyFragmentNotDetached()) {
            runningTeamWrapper.setRunnings(results.get(RunningTeamDependencyService.CODE_RUNNINGS));
            runningTeamWrapper.setTeams(results.get(RunningTeamDependencyService.CODE_TEAMS));
            runningTeamWrapper.setAcademicLevels(results.get(RunningTeamDependencyService.CODE_ACADEMICLEVELS));

            if (mode == Extra.MODE_NEW) {
                startFragment(RunningTeamNewFragment.newInstance(runningTeamWrapper));
            } else if (mode == Extra.MODE_EDIT) {
                startFragment(RunningTeamDetailsFragment.newInstance(runningTeamWrapper));
                readReports(runningTeamWrapper.getRunningTeam());
            }
        }
    }

    private void readReports(RunningTeam runningTeam) {
        reportService.readByRunningTeam(
                runningTeam.getRunning().getTeacher().getNumber(),
                runningTeam.getRunning().getYear(),
                runningTeam.getRunning().getProject().getCode(),
                runningTeam.getTeam().getNumber(),
                runningTeam.getAcademicLevel().getCode()
        );
    }

    void onReadReports(List<Report> reports) {
        runningTeamWrapper.setReports(reports);
    }

    @Override
    public void createRunningTeam(RunningTeam runningTeam) {
        runningTeamService.create(runningTeam);
    }

    @Override
    public void showRunningTeam(RunningTeam runningTeam) {
        mode = Extra.MODE_EDIT;
        runningTeamWrapper = new RunningTeamWrapper(runningTeam);

        startProxyFragment();
        readDependencies();
    }

    @Override
    public void updateRunningTeam(RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        runningTeamService.update(runningTeam, runningTeamToUpdate);
    }

    @Override
    public void deleteRunningTeam(RunningTeam runningTeam) {
        runningTeamService.delete(runningTeam);
    }
}
package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.fragment.IRunningTeamController;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.fragment.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.builder.running.DefaultRunningTeamBuilder;
import com.sasd13.proadmin.util.wrapper.RunningTeamWrapper;
import com.sasd13.proadmin.util.wrapper.RunningTeamsWrapper;
import com.sasd13.proadmin.service.ws.ReportService;
import com.sasd13.proadmin.service.ws.RunningTeamDependencyService;
import com.sasd13.proadmin.service.ws.RunningTeamService;

import java.util.List;

public class RunningTeamController extends Controller implements IRunningTeamController {

    private RunningTeamService runningTeamService;
    private RunningTeamDependencyService runningTeamDependencyService;
    private ReportService reportService;
    private RunningTeamsWrapper runningTeamsWrapper;
    private RunningTeamWrapper runningTeamWrapper;
    private int mode;

    public RunningTeamController(MainActivity mainActivity) {
        super(mainActivity);

        runningTeamService = new RunningTeamService(new RunningTeamServiceCaller(this, mainActivity));
        runningTeamDependencyService = new RunningTeamDependencyService(new RunningTeamServiceCaller(this, mainActivity));
        reportService = new ReportService(new ReportServiceCaller(this, mainActivity));
    }

    @Override
    public void entry() {
        listRunningTeams();
    }

    @Override
    public void listRunningTeams() {
        runningTeamsWrapper = new RunningTeamsWrapper();

        startProxyFragment();
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
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
        runningTeamDependencyService.read();
    }

    void onRetrieved(RunningTeamDependencyService.ResultHolder resultHolder) {
        if (isProxyFragmentNotDetached()) {
            runningTeamWrapper.setRunnings(resultHolder.getRunnings());
            runningTeamWrapper.setTeams(resultHolder.getTeams());
            runningTeamWrapper.setAcademicLevels(resultHolder.getAcademicLevels());

            if (mode == Extra.MODE_NEW) {
                startFragment(RunningTeamNewFragment.newInstance(runningTeamWrapper));
            } else {
                RunningTeam runningTeam = runningTeamWrapper.getRunningTeam();

                reportService.readByRunningTeam(
                        runningTeam.getRunning().getTeacher().getNumber(),
                        runningTeam.getRunning().getYear(),
                        runningTeam.getRunning().getProject().getCode(),
                        runningTeam.getTeam().getNumber(),
                        runningTeam.getAcademicLevel().getCode()
                );
            }
        }
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
        runningTeamDependencyService.read();
    }

    void onReadReports(List<Report> reports) {
        if (isProxyFragmentNotDetached()) {
            runningTeamWrapper.setReports(reports);
            startFragment(RunningTeamDetailsFragment.newInstance(runningTeamWrapper));
        }
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
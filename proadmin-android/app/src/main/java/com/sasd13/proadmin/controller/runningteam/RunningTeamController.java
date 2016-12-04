package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.RunningTeamDependencyWrapper;
import com.sasd13.proadmin.view.report.IReportController;
import com.sasd13.proadmin.view.runningteam.IRunningTeamController;
import com.sasd13.proadmin.view.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.view.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.view.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.ws.service.RunningTeamDependencyService;
import com.sasd13.proadmin.ws.service.RunningTeamService;

import java.util.List;

public class RunningTeamController extends Controller implements IRunningTeamController {

    private IReportController reportController;

    private RunningTeamsFragment runningTeamsFragment;
    private RunningTeamNewFragment runningTeamNewFragment;
    private RunningTeamDetailsFragment runningTeamDetailsFragment;

    private RunningTeamService runningTeamService;
    private RunningTeamDependencyService runningTeamDependencyService;

    public RunningTeamController(MainActivity mainActivity, IReportController reportController) {
        super(mainActivity);

        this.reportController = reportController;

        runningTeamService = new RunningTeamService(new RunningTeamServiceCaller(this, mainActivity));
        runningTeamDependencyService = new RunningTeamDependencyService(new RunningTeamServiceCaller(this, mainActivity));
    }

    @Override
    public void listRunningTeams() {
        runningTeamsFragment = RunningTeamsFragment.newInstance(this);

        startFragment(runningTeamsFragment);
        runningTeamService.read(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (!runningTeamsFragment.isDetached()) {
            runningTeamsFragment.setRunningTeams(runningTeams);
        }
    }

    @Override
    public void newRunningTeam() {
        runningTeamNewFragment = RunningTeamNewFragment.newInstance(this);

        startFragment(runningTeamNewFragment);
        runningTeamDependencyService.read();
    }

    void onRetrieved(RunningTeamDependencyWrapper dependencyWrapper) {
        if (runningTeamNewFragment != null && !runningTeamNewFragment.isDetached()) {
            runningTeamNewFragment.setDependencyWrapper(dependencyWrapper);
        }

        if (runningTeamDetailsFragment != null && !runningTeamDetailsFragment.isDetached()) {
            runningTeamDetailsFragment.setDependencyWrapper(dependencyWrapper);
        }
    }

    @Override
    public void createRunningTeam(RunningTeam runningTeam) {
        runningTeamService.create(runningTeam);
    }

    @Override
    public void showRunningTeam(RunningTeam runningTeam) {
        runningTeamDetailsFragment = RunningTeamDetailsFragment.newInstance(this, runningTeam);

        startFragment(runningTeamDetailsFragment);
        runningTeamDependencyService.read();
    }

    @Override
    public void updateRunningTeam(RunningTeam runningTeam, RunningTeam runningTeamToUpdate) {
        runningTeamService.update(runningTeam, runningTeamToUpdate);
    }

    @Override
    public void deleteRunningTeam(RunningTeam runningTeam) {
        runningTeamService.delete(runningTeam);
    }

    @Override
    public void newReport(RunningTeam runningTeam) {
        reportController.newReport(runningTeam);
    }

    @Override
    public void showReport(Report report) {
        reportController.showReport(report);
    }
}
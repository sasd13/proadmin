package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.util.wrapper.RunningTeamDependencyWrapper;
import com.sasd13.proadmin.view.IReportController;
import com.sasd13.proadmin.view.IRunningTeamController;
import com.sasd13.proadmin.view.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.view.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.view.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.ws.service.ReportService;
import com.sasd13.proadmin.ws.service.RunningTeamDependencyService;
import com.sasd13.proadmin.ws.service.RunningTeamService;

import java.util.List;

public class RunningTeamController extends Controller implements IRunningTeamController {

    private RunningTeamService runningTeamService;
    private RunningTeamDependencyService runningTeamDependencyService;
    private ReportService reportService;
    private RunningTeam runningTeam;
    private RunningTeamDependencyWrapper dependencyWrapper;
    private int mode;

    public RunningTeamController(MainActivity mainActivity) {
        super(mainActivity);

        runningTeamService = new RunningTeamService(new RunningTeamServiceCaller(this, mainActivity));
        runningTeamDependencyService = new RunningTeamDependencyService(new RunningTeamServiceCaller(this, mainActivity));
        reportService = new ReportService(new ReportServiceCaller(this, mainActivity));
        dependencyWrapper = new RunningTeamDependencyWrapper();
    }

    @Override
    public void entry() {
        listRunningTeams();
    }

    @Override
    public void listRunningTeams() {
        startProxyFragment();
        runningTeamService.readByTeacher(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        if (isProxyFragmentNotDetached()) {
            startFragment(RunningTeamsFragment.newInstance(this, runningTeams));
        }
    }

    @Override
    public void newRunningTeam() {
        mode = Extra.MODE_NEW;

        startProxyFragment();
        runningTeamDependencyService.read();
    }

    void onRetrieved(RunningTeamDependencyService.ResultHolder resultHolder) {
        if (isProxyFragmentNotDetached()) {
            dependencyWrapper.setRunnings(resultHolder.getRunnings());
            dependencyWrapper.setTeams(resultHolder.getTeams());
            dependencyWrapper.setAcademicLevels(resultHolder.getAcademicLevels());

            if (mode == Extra.MODE_EDIT) {
                startFragment(RunningTeamDetailsFragment.newInstance(this, runningTeam, dependencyWrapper));
            } else {
                startFragment(RunningTeamNewFragment.newInstance(this, dependencyWrapper));
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
        this.runningTeam = runningTeam;

        startProxyFragment();
        runningTeamDependencyService.read();
        reportService.readByRunningTeam(
                runningTeam.getRunning().getTeacher().getNumber(),
                runningTeam.getRunning().getYear(),
                runningTeam.getRunning().getProject().getCode(),
                runningTeam.getTeam().getNumber(),
                runningTeam.getAcademicLevel().getCode()
        );
    }

    void onReadReports(List<Report> reports) {
        if (isProxyFragmentNotDetached()) {
            dependencyWrapper.setReports(reports);
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

    @Override
    public void newReport(RunningTeam runningTeam) {
        ((IReportController) mainActivity.lookup(IReportController.class)).newReport(runningTeam);
    }

    @Override
    public void showReport(Report report) {
        ((IReportController) mainActivity.lookup(IReportController.class)).showReport(report);
    }
}
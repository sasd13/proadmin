package com.sasd13.proadmin.controller.runningteam;

import com.sasd13.proadmin.activity.MainActivity;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.content.Extra;
import com.sasd13.proadmin.controller.Controller;
import com.sasd13.proadmin.util.SessionHelper;
import com.sasd13.proadmin.view.runningteam.IRunningTeamController;
import com.sasd13.proadmin.view.runningteam.RunningTeamDetailsFragment;
import com.sasd13.proadmin.view.runningteam.RunningTeamNewFragment;
import com.sasd13.proadmin.view.runningteam.RunningTeamsFragment;
import com.sasd13.proadmin.ws.service.RunningTeamService;

import java.util.List;

public class RunningTeamController extends Controller implements IRunningTeamController {

    private RunningTeamService runningTeamService;
    private int mode;

    public RunningTeamController(MainActivity mainActivity) {
        super(mainActivity);

        runningTeamService = new RunningTeamService(new RunningTeamServiceCaller(this, mainActivity));
        mode = Extra.MODE_NEW;
    }

    @Override
    public void listRunningTeams() {
        runningTeamService.read(SessionHelper.getExtraIdTeacherNumber(mainActivity));
    }

    void onReadRunningTeams(List<RunningTeam> runningTeams) {
        startFragment(RunningTeamsFragment.newInstance(this, runningTeams));
    }

    @Override
    public void newRunningTeam() {
        mode = Extra.MODE_NEW;

        startFragment(RunningTeamNewFragment.newInstance(this));
    }

    void onRetrieved(List<Running> runnings, List<Team> teams, List<AcademicLevel> academicLevels) {
        switch (mode) {
            case Extra.MODE_EDIT:
                startFragment(RunningTeamDetailsFragment.newInstance(this));
                break;
            default:
                break;
        }
    }

    @Override
    public void createRunningTeam(RunningTeam runningTeam) {
        runningTeamService.create(runningTeam);
    }

    @Override
    public void showRunningTeam(RunningTeam runningTeam) {
        startFragment(RunningTeamDetailsFragment.newInstance(this, runningTeam));
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
        //TODO
    }

    @Override
    public void showReport(Report report) {
        //TODO
    }
}
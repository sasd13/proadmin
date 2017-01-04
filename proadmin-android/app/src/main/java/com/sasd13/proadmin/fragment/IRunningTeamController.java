package com.sasd13.proadmin.fragment;

import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningTeamController extends IController {

    void readRunningTeams();

    void listRunningTeams();

    void newRunningTeam();

    void createRunningTeam(RunningTeam runningTeam);

    void showRunningTeam(RunningTeam runningRunningTeam);

    void updateRunningTeam(RunningTeam runningTeam, RunningTeam runningTeamToUpdate);

    void deleteRunningTeam(RunningTeam runningTeam);
}

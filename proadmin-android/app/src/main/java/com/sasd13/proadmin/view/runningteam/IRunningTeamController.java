package com.sasd13.proadmin.view.runningteam;

import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.view.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningTeamController extends IController {

    void listRunningTeams();

    void newRunningTeam();

    void createRunningTeam(RunningTeam runningTeam);

    void showRunningTeam(RunningTeam runningRunningTeam);

    void updateRunningTeam(RunningTeam runningTeam, RunningTeam runningTeamToUpdate);

    void deleteRunningTeam(RunningTeam runningTeam);

    void newReport(RunningTeam runningTeam);

    void showReport(Report report);
}

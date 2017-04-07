package com.sasd13.proadmin.view.fragment.runningteam;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.view.fragment.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningTeamController extends IController {

    void actionNewRunningTeam();

    void actionCreateRunningTeam(RunningTeam runningTeam);

    void actionShowRunningTeam(RunningTeam runningTeam);

    void actionUpdateRunningTeam(RunningTeam runningTeam, RunningTeam runningTeamToUpdate);

    void actionRemoveRunningTeam(RunningTeam runningTeam);
}

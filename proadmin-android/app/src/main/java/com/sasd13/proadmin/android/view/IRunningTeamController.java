package com.sasd13.proadmin.android.view;

import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.update.RunningTeamUpdate;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningTeamController extends IController, IBrowsable {

    void actionLoadRunningTeams();

    void actionNewRunningTeam();

    void actionCreateRunningTeam(RunningTeam runningTeam);

    void actionShowRunningTeam(RunningTeam runningTeam);

    void actionUpdateRunningTeam(RunningTeamUpdate runningTeamUpdate);

    void actionRemoveRunningTeam(RunningTeam runningTeam);
}

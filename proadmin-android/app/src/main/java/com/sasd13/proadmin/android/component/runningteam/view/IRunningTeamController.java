package com.sasd13.proadmin.android.component.runningteam.view;

import com.sasd13.proadmin.android.model.RunningTeam;
import com.sasd13.proadmin.android.util.browser.IBrowsable;
import com.sasd13.proadmin.android.component.IController;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningTeamController extends IController, IBrowsable {

    void actionReadRunningTeams();

    void actionNewRunningTeam();

    void actionCreateRunningTeam(RunningTeam runningTeam);

    void actionShowRunningTeam(RunningTeam runningTeam);

    void actionUpdateRunningTeam(RunningTeam runningTeam);

    void actionRemoveRunningTeam(RunningTeam runningTeam);
}

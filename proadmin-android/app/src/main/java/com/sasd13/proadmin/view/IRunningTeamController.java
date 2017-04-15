package com.sasd13.proadmin.view;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.update.running.RunningTeamUpdateWrapper;

/**
 * Created by ssaidali2 on 04/12/2016.
 */

public interface IRunningTeamController extends IController, IBrowsable {

    void actionNewRunningTeam();

    void actionCreateRunningTeam(RunningTeam runningTeam);

    void actionShowRunningTeam(RunningTeam runningTeam);

    void actionUpdateRunningTeam(RunningTeamUpdateWrapper runningTeamUpdateWrapper);

    void actionRemoveRunningTeam(RunningTeam runningTeam);
}

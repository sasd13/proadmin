package com.sasd13.proadmin.wrapper.impl;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.wrapper.IRunningTeamReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class RunningTeamReadWrapper implements IRunningTeamReadWrapper {

    private List<RunningTeam> runningTeams;

    public RunningTeamReadWrapper(List<RunningTeam> runningTeams) {
        this.runningTeams = runningTeams;
    }

    @Override
    public List<RunningTeam> getRunningTeams() {
        return runningTeams;
    }
}

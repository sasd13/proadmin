package com.sasd13.proadmin.util.wrapper.read.running;

import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.wrapper.read.IReadWrapper;

import java.util.List;

/**
 * Created by ssaidali2 on 10/11/2016.
 */

public class RunningTeamReadWrapper implements IReadWrapper<RunningTeam> {

    private List<RunningTeam> runningTeams;

    public RunningTeamReadWrapper(List<RunningTeam> runningTeams) {
        this.runningTeams = runningTeams;
    }

    @Override
    public List<RunningTeam> getWrapped() {
        return runningTeams;
    }
}

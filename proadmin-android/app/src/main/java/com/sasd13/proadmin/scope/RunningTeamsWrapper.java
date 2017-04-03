package com.sasd13.proadmin.scope;

import com.sasd13.proadmin.bean.running.RunningTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by ssaidali2 on 06/12/2016.
 */

public class RunningTeamsWrapper extends Observable {

    private List<RunningTeam> runningTeams;

    public RunningTeamsWrapper() {
        runningTeams = new ArrayList<>();
    }

    public List<RunningTeam> getRunningTeams() {
        return runningTeams;
    }

    public void setRunningTeams(List<RunningTeam> runningTeams) {
        this.runningTeams = runningTeams;

        setChanged();
        notifyObservers();
    }
}

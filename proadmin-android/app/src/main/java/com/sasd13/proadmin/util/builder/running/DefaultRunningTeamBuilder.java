package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class DefaultRunningTeamBuilder implements IBuilder<RunningTeam> {

    private Running running;
    private Team team;

    public DefaultRunningTeamBuilder() {
    }

    public DefaultRunningTeamBuilder(Running running) {
        this.running = running;
    }

    public DefaultRunningTeamBuilder(Team team) {
        this.team = team;
    }

    @Override
    public RunningTeam build() {
        RunningTeam runningTeam = new RunningTeam();

        if (running != null) {
            runningTeam.setRunning(running);
        } else {
            runningTeam.setRunning(new Running(Calendar.getInstance().get(Calendar.YEAR), null, null));
        }

        if (team != null) {
            runningTeam.setTeam(team);
        }

        return runningTeam;
    }
}

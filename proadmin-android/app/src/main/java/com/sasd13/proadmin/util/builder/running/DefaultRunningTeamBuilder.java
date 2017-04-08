package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class DefaultRunningTeamBuilder implements IBuilder<RunningTeam> {

    private Running running;
    private Team team;
    private AcademicLevel academicLevel;

    public DefaultRunningTeamBuilder() {
        running = new DefaultRunningBuilder().build();
    }

    public DefaultRunningTeamBuilder(Running running, Team team, AcademicLevel academicLevel) {
        this.running = running;
        this.team = team;
        this.academicLevel = academicLevel;
    }

    @Override
    public RunningTeam build() {
        RunningTeam runningTeam = new RunningTeam();

        runningTeam.setRunning(running);
        runningTeam.setTeam(team);
        runningTeam.setAcademicLevel(academicLevel);

        return runningTeam;
    }
}

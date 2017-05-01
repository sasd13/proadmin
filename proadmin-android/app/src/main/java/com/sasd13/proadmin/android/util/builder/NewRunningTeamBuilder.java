package com.sasd13.proadmin.android.util.builder;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.android.bean.AcademicLevel;
import com.sasd13.proadmin.android.bean.Running;
import com.sasd13.proadmin.android.bean.RunningTeam;
import com.sasd13.proadmin.android.bean.Team;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class NewRunningTeamBuilder implements IBuilder<RunningTeam> {

    private Running running;
    private Team team;
    private AcademicLevel academicLevel;

    public NewRunningTeamBuilder() {
        running = new Running();

        running.setYear(Calendar.getInstance().get(Calendar.YEAR));
    }

    public NewRunningTeamBuilder(Running running, Team team, AcademicLevel academicLevel) {
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

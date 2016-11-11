package com.sasd13.proadmin.util.builder.member;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.util.builder.running.RunningTeamBaseBuilder;

import java.util.Calendar;

/**
 * Created by ssaidali2 on 23/07/2016.
 */
public class DefaultRunningTeamBuilder implements IBuilder<RunningTeam> {

    @Override
    public RunningTeam build() {
        RunningTeam runningTeam = new RunningTeamBaseBuilder(
                Calendar.getInstance().get(Calendar.YEAR),
                null,
                null,
                null,
                null).build();

        return runningTeam;
    }
}

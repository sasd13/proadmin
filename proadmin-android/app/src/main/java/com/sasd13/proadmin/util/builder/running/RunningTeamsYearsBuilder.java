package com.sasd13.proadmin.util.builder.running;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.running.RunningTeam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ssaidali2 on 15/08/2016.
 */
public class RunningTeamsYearsBuilder implements IBuilder<List<Integer>> {

    private List<RunningTeam> runningTeams;

    public RunningTeamsYearsBuilder(List<RunningTeam> runningTeams) {
        this.runningTeams = runningTeams;
    }

    @Override
    public List<Integer> build() {
        List<Integer> list = new ArrayList<>();

        for (RunningTeam runningTeam : runningTeams) {
            if (!list.contains(runningTeam.getRunning().getYear())) {
                list.add(runningTeam.getRunning().getYear());
            }
        }

        return list;
    }
}

package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.model.RunningTeam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RunningTeamSorter {

    public static void byRunningYearAndProjectCode(List<RunningTeam> list) {
        byRunningYearAndProjectCode(list, true);
    }

    public static void byRunningYearAndProjectCode(List<RunningTeam> list, final boolean byDesc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<RunningTeam>() {

                @Override
                public int compare(RunningTeam item1, RunningTeam item2) {
                    int value;

                    if (!byDesc) {
                        value = item1.getRunning().getYear() - item2.getRunning().getYear();
                    } else {
                        value = item2.getRunning().getYear() - item1.getRunning().getYear();
                    }

                    if (value == 0) {
                        value = item1.getRunning().getProject().getCode().compareTo(item2.getRunning().getProject().getCode());
                    }

                    return value;
                }
            });
        }
    }
}

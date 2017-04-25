package com.sasd13.proadmin.android.util.sorter;

import com.sasd13.proadmin.android.bean.RunningTeam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RunningTeamSorter {

    public static void byRunningYear(List<RunningTeam> list) {
        byRunningYear(list, true);
    }

    public static void byRunningYear(List<RunningTeam> list, final boolean byDesc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<RunningTeam>() {

                @Override
                public int compare(RunningTeam item1, RunningTeam item2) {
                    if (!byDesc) {
                        return Integer.compare(item1.getRunning().getYear(), item2.getRunning().getYear());
                    } else {
                        return Integer.compare(item2.getRunning().getYear(), item1.getRunning().getYear());
                    }
                }
            });
        }
    }

    public static void byTeamNumber(List<RunningTeam> list) {
        byTeamNumber(list, true);
    }

    public static void byTeamNumber(List<RunningTeam> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<RunningTeam>() {

                @Override
                public int compare(RunningTeam item1, RunningTeam item2) {
                    if (byAsc) {
                        return item1.getTeam().getNumber().compareTo(item2.getTeam().getNumber());
                    } else {
                        return item2.getTeam().getNumber().compareTo(item1.getTeam().getNumber());
                    }
                }
            });
        }
    }

    public static void byAcademicLevelCode(List<RunningTeam> list) {
        byAcademicLevelCode(list, true);
    }

    public static void byAcademicLevelCode(List<RunningTeam> list, final boolean byAsc) {
        if (!list.isEmpty()) {
            Collections.sort(list, new Comparator<RunningTeam>() {

                @Override
                public int compare(RunningTeam item1, RunningTeam item2) {
                    if (byAsc) {
                        return item1.getAcademicLevel().getCode().compareTo(item2.getAcademicLevel().getCode());
                    } else {
                        return item2.getAcademicLevel().getCode().compareTo(item1.getAcademicLevel().getCode());
                    }
                }
            });
        }
    }
}

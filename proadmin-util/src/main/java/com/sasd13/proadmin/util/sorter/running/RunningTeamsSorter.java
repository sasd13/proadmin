package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.RunningTeam;

public class RunningTeamsSorter {

	public static void byRunningYear(List<RunningTeam> runningTeams) {
		byRunningYear(runningTeams, false);
	}

	public static void byRunningYear(List<RunningTeam> runningTeams, final boolean byDesc) {
		Collections.sort(runningTeams, new Comparator<RunningTeam>() {

			@Override
			public int compare(RunningTeam runningTeam1, RunningTeam runningTeam2) {
				if (byDesc) {
					return Integer.compare(runningTeam2.getRunning().getYear(), runningTeam1.getRunning().getYear());
				} else {
					return Integer.compare(runningTeam1.getRunning().getYear(), runningTeam2.getRunning().getYear());
				}
			}
		});
	}

	public static void byTeamNumber(List<RunningTeam> runningTeams) {
		byTeamNumber(runningTeams, false);
	}

	public static void byTeamNumber(List<RunningTeam> runningTeams, final boolean byAsc) {
		Collections.sort(runningTeams, new Comparator<RunningTeam>() {

			@Override
			public int compare(RunningTeam runningTeam1, RunningTeam runningTeam2) {
				if (byAsc) {
					return runningTeam1.getTeam().getNumber().compareTo(runningTeam2.getTeam().getNumber());
				} else {
					return runningTeam2.getTeam().getNumber().compareTo(runningTeam1.getTeam().getNumber());
				}
			}
		});
	}
}

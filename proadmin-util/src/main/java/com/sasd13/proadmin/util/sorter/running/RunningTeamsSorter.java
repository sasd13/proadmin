package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.IRunningTeam;

public class RunningTeamsSorter {

	public static void byRunningYear(List<IRunningTeam> iRunningTeams) {
		byRunningYear(iRunningTeams, true);
	}

	public static void byRunningYear(List<IRunningTeam> iRunningTeams, final boolean byDesc) {
		if (!iRunningTeams.isEmpty()) {
			Collections.sort(iRunningTeams, new Comparator<IRunningTeam>() {

				@Override
				public int compare(IRunningTeam runningTeam1, IRunningTeam runningTeam2) {
					if (!byDesc) {
						return Integer.compare(runningTeam1.getRunning().getYear(), runningTeam2.getRunning().getYear());
					} else {
						return Integer.compare(runningTeam2.getRunning().getYear(), runningTeam1.getRunning().getYear());
					}
				}
			});
		}
	}

	public static void byTeamNumber(List<IRunningTeam> iRunningTeams) {
		byTeamNumber(iRunningTeams, true);
	}

	public static void byTeamNumber(List<IRunningTeam> iRunningTeams, final boolean byAsc) {
		if (!iRunningTeams.isEmpty()) {
			Collections.sort(iRunningTeams, new Comparator<IRunningTeam>() {

				@Override
				public int compare(IRunningTeam runningTeam1, IRunningTeam runningTeam2) {
					if (byAsc) {
						return runningTeam1.getTeam().getNumber().compareTo(runningTeam2.getTeam().getNumber());
					} else {
						return runningTeam2.getTeam().getNumber().compareTo(runningTeam1.getTeam().getNumber());
					}
				}
			});
		}
	}

	public static void byAcademicLevelCode(List<IRunningTeam> iRunningTeams) {
		byAcademicLevelCode(iRunningTeams, true);
	}

	public static void byAcademicLevelCode(List<IRunningTeam> iRunningTeams, final boolean byAsc) {
		if (!iRunningTeams.isEmpty()) {
			Collections.sort(iRunningTeams, new Comparator<IRunningTeam>() {

				@Override
				public int compare(IRunningTeam runningTeam1, IRunningTeam runningTeam2) {
					if (byAsc) {
						return runningTeam1.getAcademicLevel().getCode().compareTo(runningTeam2.getAcademicLevel().getCode());
					} else {
						return runningTeam2.getAcademicLevel().getCode().compareTo(runningTeam1.getAcademicLevel().getCode());
					}
				}
			});
		}
	}
}

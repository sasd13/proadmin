package com.sasd13.proadmin.util.sorter.running;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.sasd13.proadmin.bean.running.IRunningTeam;

public class RunningTeamSorter {

	public static void byRunningYear(List<IRunningTeam> list) {
		byRunningYear(list, true);
	}

	public static void byRunningYear(List<IRunningTeam> list, final boolean byDesc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IRunningTeam>() {

				@Override
				public int compare(IRunningTeam item1, IRunningTeam item2) {
					if (!byDesc) {
						return Integer.compare(item1.getRunning().getYear(), item2.getRunning().getYear());
					} else {
						return Integer.compare(item2.getRunning().getYear(), item1.getRunning().getYear());
					}
				}
			});
		}
	}

	public static void byTeamNumber(List<IRunningTeam> list) {
		byTeamNumber(list, true);
	}

	public static void byTeamNumber(List<IRunningTeam> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IRunningTeam>() {

				@Override
				public int compare(IRunningTeam item1, IRunningTeam item2) {
					if (byAsc) {
						return item1.getTeam().getNumber().compareTo(item2.getTeam().getNumber());
					} else {
						return item2.getTeam().getNumber().compareTo(item1.getTeam().getNumber());
					}
				}
			});
		}
	}

	public static void byAcademicLevelCode(List<IRunningTeam> list) {
		byAcademicLevelCode(list, true);
	}

	public static void byAcademicLevelCode(List<IRunningTeam> list, final boolean byAsc) {
		if (!list.isEmpty()) {
			Collections.sort(list, new Comparator<IRunningTeam>() {

				@Override
				public int compare(IRunningTeam item1, IRunningTeam item2) {
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

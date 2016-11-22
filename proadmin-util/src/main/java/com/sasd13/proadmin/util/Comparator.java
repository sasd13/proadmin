package com.sasd13.proadmin.util;

import com.sasd13.proadmin.bean.running.RunningTeam;

public class Comparator {

	public static boolean areTheSame(RunningTeam runningTeam1, RunningTeam runningTeam2) {
		return runningTeam1.getRunning().getYear() == runningTeam2.getRunning().getYear()
				&& runningTeam1.getRunning().getProject().getCode().equalsIgnoreCase(runningTeam2.getRunning().getProject().getCode())
				&& runningTeam1.getRunning().getTeacher().getNumber().equalsIgnoreCase(runningTeam2.getRunning().getTeacher().getNumber())
				&& runningTeam1.getTeam().getNumber().equalsIgnoreCase(runningTeam2.getTeam().getNumber())
				&& runningTeam1.getAcademicLevel().getCode().equalsIgnoreCase(runningTeam2.getAcademicLevel().getCode());
	}
}

package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class RunningTeamTeacherCriteria implements Criteria<RunningTeam> {

	private String intermediary;

	public RunningTeamTeacherCriteria(String intermediary) {
		this.intermediary = intermediary;
	}

	@Override
	public List<RunningTeam> meetCriteria(List<RunningTeam> list) {
		List<RunningTeam> results = new ArrayList<RunningTeam>();

		for (RunningTeam runningTeam : list) {
			if (runningTeam.getRunning().getTeacher().getIntermediary().equalsIgnoreCase(intermediary)) {
				results.add(runningTeam);
			}
		}

		return results;
	}
}

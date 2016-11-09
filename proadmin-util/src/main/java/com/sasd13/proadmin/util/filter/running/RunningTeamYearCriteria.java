package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class RunningTeamYearCriteria implements Criteria<RunningTeam> {

	private int year;

	public RunningTeamYearCriteria(int year) {
		this.year = year;
	}

	@Override
	public List<RunningTeam> meetCriteria(List<RunningTeam> list) {
		List<RunningTeam> results = new ArrayList<RunningTeam>();

		for (RunningTeam runningTeam : list) {
			if (year == runningTeam.getRunning().getYear()) {
				results.add(runningTeam);
			}
		}

		return results;
	}
}

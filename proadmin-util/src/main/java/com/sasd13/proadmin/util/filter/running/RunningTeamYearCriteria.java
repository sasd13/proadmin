package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IRunningTeam;

public class RunningTeamYearCriteria implements Criteria<IRunningTeam> {

	private int year;

	public RunningTeamYearCriteria(int year) {
		this.year = year;
	}

	@Override
	public List<IRunningTeam> meetCriteria(List<IRunningTeam> list) {
		List<IRunningTeam> results = new ArrayList<IRunningTeam>();

		for (IRunningTeam iRunningTeam : list) {
			if (year == iRunningTeam.getRunning().getYear()) {
				results.add(iRunningTeam);
			}
		}

		return results;
	}
}

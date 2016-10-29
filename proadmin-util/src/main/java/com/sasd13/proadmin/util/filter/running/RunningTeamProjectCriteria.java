package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class RunningTeamProjectCriteria implements Criteria<RunningTeam> {

	private String number;

	public RunningTeamProjectCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<RunningTeam> meetCriteria(List<RunningTeam> list) {
		List<RunningTeam> results = new ArrayList<RunningTeam>();

		for (RunningTeam runningTeam : list) {
			if (number.equalsIgnoreCase(runningTeam.getRunning().getProject().getCode())) {
				results.add(runningTeam);
			}
		}

		return results;
	}
}

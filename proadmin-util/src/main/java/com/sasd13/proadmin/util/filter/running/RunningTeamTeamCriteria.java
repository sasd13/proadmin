package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class RunningTeamTeamCriteria implements Criteria<RunningTeam> {

	private String number;

	public RunningTeamTeamCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<RunningTeam> meetCriteria(List<RunningTeam> list) {
		List<RunningTeam> results = new ArrayList<RunningTeam>();

		for (RunningTeam runningTeam : list) {
			if (runningTeam.getTeam().getNumber().equalsIgnoreCase(number)) {
				results.add(runningTeam);
			}
		}

		return results;
	}
}

package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.RunningTeam;

public class TeamCriteria implements Criteria<RunningTeam> {

	private long id;

	public TeamCriteria(long id) {
		this.id = id;
	}

	@Override
	public List<RunningTeam> meetCriteria(List<RunningTeam> list) {
		List<RunningTeam> results = new ArrayList<RunningTeam>();

		for (RunningTeam runningTeam : list) {
			if (id == runningTeam.getTeam().getId()) {
				results.add(runningTeam);
			}
		}

		return results;
	}
}

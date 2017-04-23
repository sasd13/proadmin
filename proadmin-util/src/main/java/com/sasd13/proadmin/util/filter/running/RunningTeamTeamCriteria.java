package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IRunningTeam;

public class RunningTeamTeamCriteria implements Criteria<IRunningTeam> {

	private String number;

	public RunningTeamTeamCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<IRunningTeam> meetCriteria(List<IRunningTeam> list) {
		List<IRunningTeam> results = new ArrayList<IRunningTeam>();

		for (IRunningTeam iRunningTeam : list) {
			if (iRunningTeam.getTeam().getNumber().equalsIgnoreCase(number)) {
				results.add(iRunningTeam);
			}
		}

		return results;
	}
}

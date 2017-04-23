package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IRunningTeam;

public class RunningTeamProjectCriteria implements Criteria<IRunningTeam> {

	private String code;

	public RunningTeamProjectCriteria(String code) {
		this.code = code;
	}

	@Override
	public List<IRunningTeam> meetCriteria(List<IRunningTeam> list) {
		List<IRunningTeam> results = new ArrayList<IRunningTeam>();

		for (IRunningTeam iRunningTeam : list) {
			if (iRunningTeam.getRunning().getProject().getCode().equalsIgnoreCase(code)) {
				results.add(iRunningTeam);
			}
		}

		return results;
	}
}

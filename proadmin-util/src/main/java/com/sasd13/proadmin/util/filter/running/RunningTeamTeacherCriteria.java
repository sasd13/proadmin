package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IRunningTeam;

public class RunningTeamTeacherCriteria implements Criteria<IRunningTeam> {

	private String intermediary;

	public RunningTeamTeacherCriteria(String intermediary) {
		this.intermediary = intermediary;
	}

	@Override
	public List<IRunningTeam> meetCriteria(List<IRunningTeam> list) {
		List<IRunningTeam> results = new ArrayList<IRunningTeam>();

		for (IRunningTeam item : list) {
			if (item.getRunning().getTeacher().getIntermediary().equalsIgnoreCase(intermediary)) {
				results.add(item);
			}
		}

		return results;
	}
}

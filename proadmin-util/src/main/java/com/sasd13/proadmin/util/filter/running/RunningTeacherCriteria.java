package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IRunning;

public class RunningTeacherCriteria implements Criteria<IRunning> {

	private String intermediary;

	public RunningTeacherCriteria(String intermediary) {
		this.intermediary = intermediary;
	}

	@Override
	public List<IRunning> meetCriteria(List<IRunning> list) {
		List<IRunning> results = new ArrayList<IRunning>();

		for (IRunning iRunning : list) {
			if (iRunning.getTeacher().getIntermediary().equalsIgnoreCase(intermediary)) {
				results.add(iRunning);
			}
		}

		return results;
	}
}

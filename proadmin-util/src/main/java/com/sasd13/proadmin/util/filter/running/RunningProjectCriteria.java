package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.IRunning;

public class RunningProjectCriteria implements Criteria<IRunning> {

	private String code;

	public RunningProjectCriteria(String code) {
		this.code = code;
	}

	@Override
	public List<IRunning> meetCriteria(List<IRunning> list) {
		List<IRunning> results = new ArrayList<IRunning>();

		for (IRunning item : list) {
			if (item.getProject().getCode().equalsIgnoreCase(code)) {
				results.add(item);
			}
		}

		return results;
	}
}

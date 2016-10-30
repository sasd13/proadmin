package com.sasd13.proadmin.util.filter.running;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.running.Running;

public class RunningProjectCriteria implements Criteria<Running> {

	private String code;

	public RunningProjectCriteria(String code) {
		this.code = code;
	}

	@Override
	public List<Running> meetCriteria(List<Running> list) {
		List<Running> results = new ArrayList<Running>();

		for (Running running : list) {
			if (code.equalsIgnoreCase(running.getProject().getCode())) {
				results.add(running);
			}
		}

		return results;
	}
}
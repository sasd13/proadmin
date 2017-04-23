package com.sasd13.proadmin.util.filter.project;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.project.IProject;

public class ProjectCodeCriteria implements Criteria<IProject> {

	private String code;

	public ProjectCodeCriteria(String code) {
		this.code = code;
	}

	@Override
	public List<IProject> meetCriteria(List<IProject> list) {
		List<IProject> results = new ArrayList<IProject>();

		for (IProject item : list) {
			if (item.getCode().equalsIgnoreCase(code)) {
				results.add(item);
			}
		}

		return results;
	}
}

package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.Team;

public class CodeCriteria implements Criteria<Team> {

	private String code;

	public CodeCriteria(String code) {
		this.code = code;
	}

	@Override
	public List<Team> meetCriteria(List<Team> list) {
		List<Team> results = new ArrayList<Team>();

		for (Team team : list) {
			if (code.equalsIgnoreCase(team.getCode())) {
				results.add(team);
			}
		}

		return results;
	}
}

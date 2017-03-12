package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.Team;

public class TeamNumberCriteria implements Criteria<Team> {

	private String number;

	public TeamNumberCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<Team> meetCriteria(List<Team> list) {
		List<Team> results = new ArrayList<Team>();

		for (Team team : list) {
			if (team.getNumber().equalsIgnoreCase(number)) {
				results.add(team);
			}
		}

		return results;
	}
}

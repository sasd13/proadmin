package com.sasd13.proadmin.util.filter.member;

import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.pattern.filter.Criteria;
import com.sasd13.proadmin.bean.member.ITeam;

public class TeamNumberCriteria implements Criteria<ITeam> {

	private String number;

	public TeamNumberCriteria(String number) {
		this.number = number;
	}

	@Override
	public List<ITeam> meetCriteria(List<ITeam> list) {
		List<ITeam> results = new ArrayList<ITeam>();

		for (ITeam item : list) {
			if (item.getNumber().equalsIgnoreCase(number)) {
				results.add(item);
			}
		}

		return results;
	}
}

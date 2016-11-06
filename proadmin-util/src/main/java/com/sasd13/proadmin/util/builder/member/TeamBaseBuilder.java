package com.sasd13.proadmin.util.builder.member;

import com.sasd13.javaex.pattern.builder.IBuilder;
import com.sasd13.proadmin.bean.member.Team;

public class TeamBaseBuilder implements IBuilder<Team> {

	private String number;

	public TeamBaseBuilder(String number) {
		this.number = number;
	}

	@Override
	public Team build() {
		Team team = new Team();
		team.setNumber(number);

		return team;
	}
}

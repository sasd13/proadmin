package com.sasd13.proadmin.util.wrapper.update.member;

import com.sasd13.proadmin.bean.member.Team;

public class TeamUpdateWrapper implements ITeamUpdateWrapper {

	private Team team;
	private String number;

	@Override
	public Team getWrapped() {
		return team;
	}

	@Override
	public void setWrapped(Team team) {
		this.team = team;
	}

	@Override
	public String getNumber() {
		return number;
	}

	@Override
	public void setNumber(String number) {
		this.number = number;
	}
}

package com.sasd13.proadmin.core.decorator;

import com.sasd13.proadmin.core.bean.running.Team;

public class TeamDecorator extends Team {
	
	private Team team;
	private boolean deleted;
	
	public TeamDecorator(Team team) {
		this.team = team;
	}
	
	public Team getTeam() {
		return team;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}

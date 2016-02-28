package com.sasd13.proadmin.core.bean.running.handler;

import java.util.List;

import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;

public class RunningHandler {
	
	public static void addTeamsToRunning(List<Team> teams, Running running) {
		for (Team team : teams) {
			running.addTeam(team);
		}
	}
}

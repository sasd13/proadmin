package com.sasd13.proadmin.core.bean.running.handler;

import java.util.List;

import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;

public class RunningHandler {
	
	public static void setTeamsToRunning(List<Team> teams, Running running) {
		List<Team> teamsTarget = running.getTeams();
		
		teamsTarget.clear();
		teamsTarget.addAll(teams);
	}
}

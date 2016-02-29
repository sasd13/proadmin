package com.sasd13.proadmin.core.bean.running.handler;

import java.util.List;

import com.sasd13.proadmin.core.bean.running.Report;
import com.sasd13.proadmin.core.bean.running.Team;

public class TeamHandler {
	
	public static void setReportsToTeam(List<Report> reports, Team team) {
		List<Report> reportsTarget = team.getReports();
		
		reportsTarget.clear();
		reportsTarget.addAll(reports);
	}
}

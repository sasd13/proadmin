package com.sasd13.proadmin.itf.bean.runningteam;

import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.academiclevel.AcademicLevelBean;
import com.sasd13.proadmin.itf.bean.running.RunningBean;
import com.sasd13.proadmin.itf.bean.team.TeamBean;

public class RunningTeamBean {

	private Id id;
	private RunningBean linkedRunning;
	private TeamBean linkedTeam;
	private AcademicLevelBean linkedAcademicLevel;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public RunningBean getLinkedRunning() {
		return linkedRunning;
	}

	public void setLinkedRunning(RunningBean linkedRunning) {
		this.linkedRunning = linkedRunning;
	}

	public TeamBean getLinkedTeam() {
		return linkedTeam;
	}

	public void setLinkedTeam(TeamBean linkedTeam) {
		this.linkedTeam = linkedTeam;
	}

	public AcademicLevelBean getLinkedAcademicLevel() {
		return linkedAcademicLevel;
	}

	public void setLinkedAcademicLevel(AcademicLevelBean linkedAcademicLevel) {
		this.linkedAcademicLevel = linkedAcademicLevel;
	}
}

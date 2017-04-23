package com.sasd13.proadmin.itf.bean.runningteam;

import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedInfo;

public class RunningTeamBean {

	private Id id;
	private LinkedInfo linkedRunning, linkedTeam, linkedAcademicLevel;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public LinkedInfo getLinkedRunning() {
		return linkedRunning;
	}

	public void setLinkedRunning(LinkedInfo linkedRunning) {
		this.linkedRunning = linkedRunning;
	}

	public LinkedInfo getLinkedTeam() {
		return linkedTeam;
	}

	public void setLinkedTeam(LinkedInfo linkedTeam) {
		this.linkedTeam = linkedTeam;
	}

	public LinkedInfo getLinkedAcademicLevel() {
		return linkedAcademicLevel;
	}

	public void setLinkedAcademicLevel(LinkedInfo linkedAcademicLevel) {
		this.linkedAcademicLevel = linkedAcademicLevel;
	}
}

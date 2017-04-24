package com.sasd13.proadmin.itf.bean.runningteam;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sasd13.proadmin.itf.bean.LinkedAcademicLevel;
import com.sasd13.proadmin.itf.bean.LinkedRunning;
import com.sasd13.proadmin.itf.bean.LinkedTeam;

public class RunningTeamBean {

	@JsonInclude(Include.NON_NULL)
	private Id id;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedRunning linkedRunning;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedTeam linkedTeam;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedAcademicLevel linkedAcademicLevel;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public LinkedRunning getLinkedRunning() {
		return linkedRunning;
	}

	public void setLinkedRunning(LinkedRunning linkedRunning) {
		this.linkedRunning = linkedRunning;
	}

	public LinkedTeam getLinkedTeam() {
		return linkedTeam;
	}

	public void setLinkedTeam(LinkedTeam linkedTeam) {
		this.linkedTeam = linkedTeam;
	}

	public LinkedAcademicLevel getLinkedAcademicLevel() {
		return linkedAcademicLevel;
	}

	public void setLinkedAcademicLevel(LinkedAcademicLevel linkedAcademicLevel) {
		this.linkedAcademicLevel = linkedAcademicLevel;
	}
}

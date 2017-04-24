package com.sasd13.proadmin.itf.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class LinkedRunningTeam extends LinkedInfo {

	@JsonInclude(Include.NON_NULL)
	private LinkedRunning linkedRunning;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedTeam linkedTeam;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedAcademicLevel linkedAcademicLevel;

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

package com.sasd13.proadmin.itf.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class LinkedReport extends LinkedInfo {

	private String number;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedRunningTeam linkedRunningTeam;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LinkedRunningTeam getLinkedRunningTeam() {
		return linkedRunningTeam;
	}

	public void setLinkedRunningTeam(LinkedRunningTeam linkedRunningTeam) {
		this.linkedRunningTeam = linkedRunningTeam;
	}
}

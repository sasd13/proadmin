package com.sasd13.proadmin.itf.bean;

public class LinkedReport extends LinkedInfo {

	private String number;
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

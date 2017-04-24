package com.sasd13.proadmin.itf.bean;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "linkedRunning", "linkedTeam", "linkedAcademicLevel" })
public class LinkedRunningTeam {

	@JsonProperty("id")
	private String id;

	@JsonProperty("linkedRunning")
	private LinkedRunning linkedRunning;

	@JsonProperty("linkedTeam")
	private LinkedTeam linkedTeam;

	@JsonProperty("linkedAcademicLevel")
	private LinkedAcademicLevel linkedAcademicLevel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

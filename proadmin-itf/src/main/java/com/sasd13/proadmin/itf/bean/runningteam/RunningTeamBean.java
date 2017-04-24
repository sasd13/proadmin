package com.sasd13.proadmin.itf.bean.runningteam;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.LinkedAcademicLevel;
import com.sasd13.proadmin.itf.bean.LinkedRunning;
import com.sasd13.proadmin.itf.bean.LinkedTeam;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "linkedRunning", "linkedTeam", "linkedAcademicLevel" })
public class RunningTeamBean {

	@JsonProperty("id")
	private Id id;

	@JsonProperty("linkedRunning")
	private LinkedRunning linkedRunning;

	@JsonProperty("linkedTeam")
	private LinkedTeam linkedTeam;

	@JsonProperty("linkedAcademicLevel")
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

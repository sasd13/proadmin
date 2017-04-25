package com.sasd13.proadmin.itf.bean.studentteam;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.LinkedStudent;
import com.sasd13.proadmin.itf.bean.LinkedTeam;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "linkedStudent", "linkedTeam" })
public class StudentTeamBean {

	@JsonProperty("id")
	private Id id;

	@JsonProperty("linkedStudent")
	private LinkedStudent linkedStudent;

	@JsonProperty("linkedTeam")
	private LinkedTeam linkedTeam;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public LinkedStudent getLinkedStudent() {
		return linkedStudent;
	}

	public void setLinkedStudent(LinkedStudent linkedStudent) {
		this.linkedStudent = linkedStudent;
	}

	public LinkedTeam getLinkedTeam() {
		return linkedTeam;
	}

	public void setLinkedTeam(LinkedTeam linkedTeam) {
		this.linkedTeam = linkedTeam;
	}
}

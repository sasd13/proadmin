package com.sasd13.proadmin.itf.bean;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "number", "linkedRunningTeam" })
public class LinkedReport {

	@JsonProperty("id")
	private String id;

	@JsonProperty("number")
	private String number;

	@JsonProperty("linkedRunningTeam")
	private LinkedRunningTeam linkedRunningTeam;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

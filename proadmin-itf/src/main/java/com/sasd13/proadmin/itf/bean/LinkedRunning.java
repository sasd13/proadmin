package com.sasd13.proadmin.itf.bean;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "yearStarted", "linkedProject", "linkedTeacher" })
public class LinkedRunning {

	@JsonProperty("id")
	private String id;

	@JsonProperty("yearStarted")
	private int yearStarted;

	@JsonProperty("linkedProject")
	private LinkedProject linkedProject;

	@JsonProperty("linkedTeacher")
	private LinkedTeacher linkedTeacher;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getYearStarted() {
		return yearStarted;
	}

	public void setYearStarted(int yearStarted) {
		this.yearStarted = yearStarted;
	}

	public LinkedProject getLinkedProject() {
		return linkedProject;
	}

	public void setLinkedProject(LinkedProject linkedProject) {
		this.linkedProject = linkedProject;
	}

	public LinkedTeacher getLinkedTeacher() {
		return linkedTeacher;
	}

	public void setLinkedTeacher(LinkedTeacher linkedTeacher) {
		this.linkedTeacher = linkedTeacher;
	}
}

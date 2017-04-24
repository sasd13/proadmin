package com.sasd13.proadmin.itf.bean.running;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
public class RunningBean {

	private Id id;
	private CoreInfo coreInfo;
	private LinkedProject linkedProject;
	private LinkedTeacher linkedTeacher;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public CoreInfo getCoreInfo() {
		return coreInfo;
	}

	public void setCoreInfo(CoreInfo coreInfo) {
		this.coreInfo = coreInfo;
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

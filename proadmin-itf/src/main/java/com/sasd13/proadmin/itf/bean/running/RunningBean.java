package com.sasd13.proadmin.itf.bean.running;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;

public class RunningBean {

	@JsonInclude(Include.NON_NULL)
	private Id id;
	
	private CoreInfo coreInfo;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedProject linkedProject;
	
	@JsonInclude(Include.NON_NULL)
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

package com.sasd13.proadmin.itf.bean.running;

import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedInfo;

public class RunningBean {

	private Id id;
	private CoreInfo coreInfo;
	private LinkedInfo linkedProject, linkedTeacher;

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

	public LinkedInfo getLinkedProject() {
		return linkedProject;
	}

	public void setLinkedProject(LinkedInfo linkedProject) {
		this.linkedProject = linkedProject;
	}

	public LinkedInfo getLinkedTeacher() {
		return linkedTeacher;
	}

	public void setLinkedTeacher(LinkedInfo linkedTeacher) {
		this.linkedTeacher = linkedTeacher;
	}
}

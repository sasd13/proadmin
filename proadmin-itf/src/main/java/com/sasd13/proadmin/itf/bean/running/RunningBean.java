package com.sasd13.proadmin.itf.bean.running;

import com.sasd13.proadmin.itf.bean.LinkedProject;
import com.sasd13.proadmin.itf.bean.LinkedTeacher;

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

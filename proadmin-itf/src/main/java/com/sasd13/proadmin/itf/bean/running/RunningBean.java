package com.sasd13.proadmin.itf.bean.running;

import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.project.ProjectBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class RunningBean {

	private Id id;
	private CoreInfo coreInfo;
	private ProjectBean linkedProject;
	private TeacherBean linkedTeacher;

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

	public ProjectBean getLinkedProject() {
		return linkedProject;
	}

	public void setLinkedProject(ProjectBean linkedProject) {
		this.linkedProject = linkedProject;
	}

	public TeacherBean getLinkedTeacher() {
		return linkedTeacher;
	}

	public void setLinkedTeacher(TeacherBean linkedTeacher) {
		this.linkedTeacher = linkedTeacher;
	}
}

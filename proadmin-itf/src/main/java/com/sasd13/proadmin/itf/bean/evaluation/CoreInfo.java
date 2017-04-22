package com.sasd13.proadmin.itf.bean.evaluation;

import com.sasd13.proadmin.itf.bean.project.ProjectBean;
import com.sasd13.proadmin.itf.bean.teacher.TeacherBean;

public class CoreInfo {

	private String yearStarted;
	private ProjectBean linkedProject;
	private TeacherBean linkedTeacher;

	public String getYearStarted() {
		return yearStarted;
	}

	public void setYearStarted(String yearStarted) {
		this.yearStarted = yearStarted;
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

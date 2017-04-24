package com.sasd13.proadmin.itf.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class LinkedRunning extends LinkedInfo {

	private int yearStarted;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedProject linkedProject;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedTeacher linkedTeacher;

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

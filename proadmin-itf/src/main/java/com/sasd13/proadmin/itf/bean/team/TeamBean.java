package com.sasd13.proadmin.itf.bean.team;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.student.StudentBean;

public class TeamBean {

	@JsonInclude(Include.NON_NULL)
	private Id id;
	
	private CoreInfo coreInfo;
	
	private List<StudentBean> associatedStudents;

	public TeamBean() {
		associatedStudents = new ArrayList<>();
	}

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

	public List<StudentBean> getAssociatedStudents() {
		return associatedStudents;
	}

	public void setAssociatedStudents(List<StudentBean> associatedStudents) {
		this.associatedStudents = associatedStudents;
	}
}

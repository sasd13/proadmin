package com.sasd13.proadmin.itf.bean.teacher;

import com.sasd13.proadmin.itf.bean.Id;

public class TeacherBean {

	private Id id;
	private CoreInfo coreInfo;

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
}
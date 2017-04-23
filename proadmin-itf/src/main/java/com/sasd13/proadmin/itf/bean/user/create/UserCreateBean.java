package com.sasd13.proadmin.itf.bean.user.create;

import com.sasd13.proadmin.itf.bean.user.LinkedInfo;

public class UserCreateBean {

	private CoreInfo coreInfo;
	private LinkedInfo linkedInfo;

	public CoreInfo getCoreInfo() {
		return coreInfo;
	}

	public void setCoreInfo(CoreInfo coreInfo) {
		this.coreInfo = coreInfo;
	}

	public LinkedInfo getLinkedInfo() {
		return linkedInfo;
	}

	public void setLinkedInfo(LinkedInfo linkedInfo) {
		this.linkedInfo = linkedInfo;
	}
}

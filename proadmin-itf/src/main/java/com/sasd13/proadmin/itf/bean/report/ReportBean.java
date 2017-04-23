package com.sasd13.proadmin.itf.bean.report;

import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedRunningTeam;

public class ReportBean {

	private Id id;
	private CoreInfo coreInfo;
	private LinkedRunningTeam linkedRunningTeam;

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

	public LinkedRunningTeam getLinkedRunningTeam() {
		return linkedRunningTeam;
	}

	public void setLinkedRunningTeam(LinkedRunningTeam linkedRunningTeam) {
		this.linkedRunningTeam = linkedRunningTeam;
	}
}

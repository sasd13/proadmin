package com.sasd13.proadmin.itf.bean.report;

import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.runningteam.RunningTeamBean;

public class ReportBean {

	private Id id;
	private CoreInfo coreInfo;
	private RunningTeamBean linkedRunningTeam;

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

	public RunningTeamBean getLinkedRunningTeam() {
		return linkedRunningTeam;
	}

	public void setLinkedRunningTeam(RunningTeamBean linkedRunningTeam) {
		this.linkedRunningTeam = linkedRunningTeam;
	}
}

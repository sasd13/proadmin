package com.sasd13.proadmin.itf.bean.individualevaluation;

import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedInfo;

public class IndividualEvaluationBean {

	private Id id;
	private CoreInfo coreInfo;
	private LinkedInfo linkedReport, linkedStudent;

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

	public LinkedInfo getLinkedReport() {
		return linkedReport;
	}

	public void setLinkedReport(LinkedInfo linkedReport) {
		this.linkedReport = linkedReport;
	}

	public LinkedInfo getLinkedStudent() {
		return linkedStudent;
	}

	public void setLinkedStudent(LinkedInfo linkedStudent) {
		this.linkedStudent = linkedStudent;
	}
}

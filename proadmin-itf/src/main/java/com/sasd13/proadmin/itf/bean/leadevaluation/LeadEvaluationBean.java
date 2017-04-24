package com.sasd13.proadmin.itf.bean.leadevaluation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;

public class LeadEvaluationBean {

	@JsonInclude(Include.NON_NULL)
	private Id id;
	
	private CoreInfo coreInfo;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedReport linkedReport;
	
	@JsonInclude(Include.NON_NULL)
	private LinkedStudent linkedStudent;

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

	public LinkedReport getLinkedReport() {
		return linkedReport;
	}

	public void setLinkedReport(LinkedReport linkedReport) {
		this.linkedReport = linkedReport;
	}

	public LinkedStudent getLinkedStudent() {
		return linkedStudent;
	}

	public void setLinkedStudent(LinkedStudent linkedStudent) {
		this.linkedStudent = linkedStudent;
	}
}

package com.sasd13.proadmin.itf.bean.individualevaluation;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
public class IndividualEvaluationBean {

	private Id id;
	private CoreInfo coreInfo;
	private LinkedReport linkedReport;
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

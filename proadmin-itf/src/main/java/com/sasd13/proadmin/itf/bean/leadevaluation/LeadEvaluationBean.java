package com.sasd13.proadmin.itf.bean.leadevaluation;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.LinkedReport;
import com.sasd13.proadmin.itf.bean.LinkedStudent;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "coreInfo", "linkedReport", "linkedStudent" })
public class LeadEvaluationBean {

	@JsonProperty("id")
	private Id id;

	@JsonProperty("coreInfo")
	private CoreInfo coreInfo;

	@JsonProperty("linkedReport")
	private LinkedReport linkedReport;

	@JsonProperty("linkedStudent")
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

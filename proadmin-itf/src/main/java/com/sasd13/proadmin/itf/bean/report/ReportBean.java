package com.sasd13.proadmin.itf.bean.report;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.Id;
import com.sasd13.proadmin.itf.bean.LinkedRunningTeam;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "coreInfo", "linkedRunningTeam" })
public class ReportBean {

	@JsonProperty("id")
	private Id id;

	@JsonProperty("coreInfo")
	private CoreInfo coreInfo;

	@JsonProperty("linkedRunningTeam")
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

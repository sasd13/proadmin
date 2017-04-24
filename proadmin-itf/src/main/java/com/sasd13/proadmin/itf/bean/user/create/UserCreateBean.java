package com.sasd13.proadmin.itf.bean.user.create;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.user.LinkedCredential;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "coreInfo", "linkedCredential" })
public class UserCreateBean {

	@JsonProperty("coreInfo")
	private CoreInfo coreInfo;

	@JsonProperty("linkedCredential")
	private LinkedCredential linkedCredential;

	public CoreInfo getCoreInfo() {
		return coreInfo;
	}

	public void setCoreInfo(CoreInfo coreInfo) {
		this.coreInfo = coreInfo;
	}

	public LinkedCredential getLinkedCredential() {
		return linkedCredential;
	}

	public void setLinkedCredential(LinkedCredential linkedCredential) {
		this.linkedCredential = linkedCredential;
	}
}

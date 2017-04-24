package com.sasd13.proadmin.itf.bean.user.create;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.user.LinkedInfo;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
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

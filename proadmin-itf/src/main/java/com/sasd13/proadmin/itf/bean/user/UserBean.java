package com.sasd13.proadmin.itf.bean.user;

import java.util.List;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.Id;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id", "coreInfo", "linkedPreferences" })
public class UserBean {

	@JsonProperty("id")
	private Id id;

	@JsonProperty("coreInfo")
	private CoreInfo coreInfo;

	@JsonProperty("linkedPreferences")
	private List<UserPreferenceBean> linkedPreferences;

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

	public List<UserPreferenceBean> getLinkedPreferences() {
		return linkedPreferences;
	}

	public void setLinkedPreferences(List<UserPreferenceBean> linkedPreferences) {
		this.linkedPreferences = linkedPreferences;
	}
}

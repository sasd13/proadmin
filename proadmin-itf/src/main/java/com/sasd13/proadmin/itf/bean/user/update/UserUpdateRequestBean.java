package com.sasd13.proadmin.itf.bean.user.update;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.RequestBean;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "data" })
public class UserUpdateRequestBean extends RequestBean {

	@JsonProperty("data")
	private UserUpdateBean data;

	public UserUpdateBean getData() {
		return data;
	}

	public void setData(UserUpdateBean data) {
		this.data = data;
	}
}

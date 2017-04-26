package com.sasd13.proadmin.itf.bean.user.create;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.RequestBean;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "data" })
public class UserCreateRequestBean extends RequestBean {

	@JsonProperty("data")
	private UserCreateBean data;

	public UserCreateBean getData() {
		return data;
	}

	public void setData(UserCreateBean data) {
		this.data = data;
	}
}

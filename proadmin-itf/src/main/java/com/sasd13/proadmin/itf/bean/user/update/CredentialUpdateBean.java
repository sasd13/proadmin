package com.sasd13.proadmin.itf.bean.user.update;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.user.LinkedCredential;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "previous", "current" })
public class CredentialUpdateBean {

	@JsonProperty("previous")
	private LinkedCredential previous;

	@JsonProperty("current")
	private LinkedCredential current;

	public LinkedCredential getPrevious() {
		return previous;
	}

	public void setPrevious(LinkedCredential previous) {
		this.previous = previous;
	}

	public LinkedCredential getCurrent() {
		return current;
	}

	public void setCurrent(LinkedCredential current) {
		this.current = current;
	}
}

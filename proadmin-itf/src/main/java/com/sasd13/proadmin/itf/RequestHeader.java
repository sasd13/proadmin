package com.sasd13.proadmin.itf;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({ "applicativeContext" })
public class RequestHeader {

	@JsonProperty("applicativeContext")
	private RequestApplicativeContext applicativeContext;

	public RequestHeader() {
		applicativeContext = new RequestApplicativeContext();
	}

	public RequestApplicativeContext getApplicativeContext() {
		return applicativeContext;
	}

	public void setApplicativeContext(RequestApplicativeContext applicativeContext) {
		this.applicativeContext = applicativeContext;
	}
}
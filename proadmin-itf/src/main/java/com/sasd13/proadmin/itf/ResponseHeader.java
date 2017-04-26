package com.sasd13.proadmin.itf;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({ "applicativeContext" })
public class ResponseHeader {

	@JsonProperty("applicativeContext")
	private ResponseApplicativeContext applicativeContext;

	public ResponseHeader() {
		applicativeContext = new ResponseApplicativeContext();
	}

	public ResponseApplicativeContext getApplicativeContext() {
		return applicativeContext;
	}

	public void setApplicativeContext(ResponseApplicativeContext applicativeContext) {
		this.applicativeContext = applicativeContext;
	}
}
package com.sasd13.proadmin.itf;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({ "header" })
public abstract class RequestBean {

	@JsonProperty("header")
	private RequestHeader header;

	public RequestBean() {
		header = new RequestHeader();
	}

	public RequestHeader getHeader() {
		return header;
	}

	public void setHeader(RequestHeader header) {
		this.header = header;
	}
}

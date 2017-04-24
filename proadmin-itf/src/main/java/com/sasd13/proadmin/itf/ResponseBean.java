package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "context", "errors", "data" })
public class ResponseBean {

	@JsonProperty("context")
	private ResponseContext context;

	@JsonProperty("errors")
	private Map<String, String> errors;

	@JsonProperty("data")
	private Object data;

	public ResponseBean() {
		context = new ResponseContext();
		errors = new HashMap<>();
	}

	public ResponseContext getContext() {
		return context;
	}

	public void setContext(ResponseContext context) {
		this.context = context;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}

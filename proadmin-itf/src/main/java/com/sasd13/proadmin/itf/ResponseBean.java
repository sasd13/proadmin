package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

public class ResponseBean {

	private ResponseContext context;
	private Map<String, String> errors;

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
}

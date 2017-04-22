package com.sasd13.proadmin.itf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseBean<T> {

	private ResponseContext context;
	private Map<String, String> errors;
	private List<T> data;

	public ResponseBean() {
		context = new ResponseContext();
		errors = new HashMap<>();
		data = new ArrayList<>();
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

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}

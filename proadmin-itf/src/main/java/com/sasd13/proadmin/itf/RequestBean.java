package com.sasd13.proadmin.itf;

import java.util.HashMap;
import java.util.Map;

public class RequestBean {

	private RequestContext context;
	private Map<String, String[]> criteria;

	public RequestBean() {
		context = new RequestContext();
		criteria = new HashMap<>();
	}

	public RequestContext getContext() {
		return context;
	}

	public void setContext(RequestContext context) {
		this.context = context;
	}

	public Map<String, String[]> getCriteria() {
		return criteria;
	}

	public void setCriteria(Map<String, String[]> criteria) {
		this.criteria = criteria;
	}
}

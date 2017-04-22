package com.sasd13.proadmin.itf;

public class RequestBean {

	private RequestContext context;

	public RequestBean() {
		context = new RequestContext();
	}

	public RequestContext getContext() {
		return context;
	}

	public void setContext(RequestContext context) {
		this.context = context;
	}
}

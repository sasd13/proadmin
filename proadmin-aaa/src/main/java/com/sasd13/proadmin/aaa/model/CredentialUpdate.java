package com.sasd13.proadmin.aaa.model;

import com.sasd13.javaex.security.Credential;

public class CredentialUpdate {

	private Credential previous, current;

	public Credential getPrevious() {
		return previous;
	}

	public void setPrevious(Credential previous) {
		this.previous = previous;
	}

	public Credential getCurrent() {
		return current;
	}

	public void setCurrent(Credential current) {
		this.current = current;
	}
}

package com.sasd13.proadmin.aaa.bean;

import com.sasd13.javaex.security.Credential;

public class UserCreate {

	private User user;
	private Credential credential;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}
}

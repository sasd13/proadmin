package com.sasd13.proadmin.aaa.model;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.bean.user.User;

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

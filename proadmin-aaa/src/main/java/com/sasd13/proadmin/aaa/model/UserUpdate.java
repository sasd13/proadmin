package com.sasd13.proadmin.aaa.model;

import com.sasd13.proadmin.bean.user.User;

public class UserUpdate {

	private User user;
	private CredentialUpdate credentials;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CredentialUpdate getCredentials() {
		return credentials;
	}

	public void setCredentials(CredentialUpdate credentials) {
		this.credentials = credentials;
	}
}

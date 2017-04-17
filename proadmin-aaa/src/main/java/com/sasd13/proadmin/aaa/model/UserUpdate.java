package com.sasd13.proadmin.aaa.model;

public class UserUpdate {

	private User user;
	private CredentialUpdate credentials;
	private String userID;

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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}

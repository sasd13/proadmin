package com.sasd13.proadmin.itf.bean.user;

public class UserUpdateBean {

	private UserBean user;
	private CredentialUpdateBean credentials;
	private String userID;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public CredentialUpdateBean getCredentials() {
		return credentials;
	}

	public void setCredentials(CredentialUpdateBean credentials) {
		this.credentials = credentials;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}

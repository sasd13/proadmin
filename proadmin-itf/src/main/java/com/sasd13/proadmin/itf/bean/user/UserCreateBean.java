package com.sasd13.proadmin.itf.bean.user;

import com.sasd13.javaex.security.Credential;

public class UserCreateBean {

	private UserBean user;
	private Credential credential;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}
}

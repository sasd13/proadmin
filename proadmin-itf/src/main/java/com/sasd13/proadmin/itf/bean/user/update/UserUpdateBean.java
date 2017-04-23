package com.sasd13.proadmin.itf.bean.user.update;

import com.sasd13.proadmin.itf.bean.user.UserBean;

public class UserUpdateBean {

	private UserBean user;
	private CredentialUpdateBean cedits;

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

	public CredentialUpdateBean getCedits() {
		return cedits;
	}

	public void setCedits(CredentialUpdateBean cedits) {
		this.cedits = cedits;
	}
}

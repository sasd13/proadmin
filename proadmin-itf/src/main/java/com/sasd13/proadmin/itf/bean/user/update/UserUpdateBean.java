package com.sasd13.proadmin.itf.bean.user.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.sasd13.proadmin.itf.bean.user.UserBean;

public class UserUpdateBean {

	@JsonInclude(Include.NON_NULL)
	private UserBean user;
	
	@JsonInclude(Include.NON_NULL)
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

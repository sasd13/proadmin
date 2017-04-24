package com.sasd13.proadmin.itf.bean.user.update;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.user.UserBean;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "id" })
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

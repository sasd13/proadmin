package com.sasd13.proadmin.itf.bean.user.update;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sasd13.proadmin.itf.bean.user.UserBean;

@Generated("org.jsonschema2pojo")
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({ "user", "credentials" })
public class UserUpdateBean {

	@JsonProperty("user")
	private UserBean user;

	@JsonProperty("credentials")
	private CredentialUpdateBean credentials;

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
}

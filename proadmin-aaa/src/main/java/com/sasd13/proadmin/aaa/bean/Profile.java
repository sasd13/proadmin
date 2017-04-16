package com.sasd13.proadmin.aaa.bean;

import com.sasd13.javaex.security.Credential;

public class Profile extends Credential {

	private Credential credential;
	private String number;

	public Profile(Credential credential) {
		this.credential = credential;
	}

	@Override
	public String getUsername() {
		return credential.getUsername();
	}

	@Override
	public void setUsername(String username) {
		credential.setUsername(username);
	}
	
	@Override
	public String getPassword() {
		return credential.getPassword();
	}
	
	@Override
	public void setPassword(String password) {
		credential.setPassword(password);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}

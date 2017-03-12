package com.sasd13.proadmin.util.wrapper.update.credential;

import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;

public class CredentialUpdateWrapper implements IUpdateWrapper<Credential> {

	private Credential credential;
	private String username;

	@Override
	public Credential getWrapped() {
		return credential;
	}

	@Override
	public void setWrapped(Credential credential) {
		this.credential = credential;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

package com.sasd13.proadmin.util.wrapper.update.credential;

import com.sasd13.javaex.security.Credential;

public class CredentialUpdateWrapper implements ICredentialUpdateWrapper {

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

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}
}

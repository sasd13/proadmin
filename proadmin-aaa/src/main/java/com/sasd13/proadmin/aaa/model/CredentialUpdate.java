package com.sasd13.proadmin.aaa.model;

import com.sasd13.javaex.security.Credential;

public class CredentialUpdate {

	private Credential oldCredential, newCredential;

	public Credential getOldCredential() {
		return oldCredential;
	}

	public void setOldCredential(Credential oldCredential) {
		this.oldCredential = oldCredential;
	}

	public Credential getNewCredential() {
		return newCredential;
	}

	public void setNewCredential(Credential newCredential) {
		this.newCredential = newCredential;
	}
}

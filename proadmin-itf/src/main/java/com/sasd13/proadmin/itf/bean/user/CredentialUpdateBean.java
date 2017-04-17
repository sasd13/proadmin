package com.sasd13.proadmin.itf.bean.user;

import com.sasd13.javaex.security.Credential;

public class CredentialUpdateBean {

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

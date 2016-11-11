package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.security.Credential;

public interface ICredentialUpdateWrapper extends IUpdateWrapper<Credential> {

	String getUsername();

	void setUsername(String username);
}

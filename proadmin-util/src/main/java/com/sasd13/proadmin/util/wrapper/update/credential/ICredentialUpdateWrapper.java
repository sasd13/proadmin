package com.sasd13.proadmin.util.wrapper.update.credential;

import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;

public interface ICredentialUpdateWrapper extends IUpdateWrapper<Credential> {

	String getUsername();

	void setUsername(String username);
}

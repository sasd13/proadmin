package com.sasd13.proadmin.aaa.service;

import com.sasd13.proadmin.aaa.Credential;

public interface ICredentialManageService {

	boolean createCredential(Credential credential);
	
	boolean updateCredential(Credential credential);
}

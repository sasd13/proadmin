package com.sasd13.proadmin.aaa.service;

import com.sasd13.proadmin.aaa.AAAException;
import com.sasd13.proadmin.aaa.bean.Credential;

public interface ICredentialManageService {

	void createCredential(Credential credential) throws AAAException;

	void updateCredential(Credential credential) throws AAAException;
}

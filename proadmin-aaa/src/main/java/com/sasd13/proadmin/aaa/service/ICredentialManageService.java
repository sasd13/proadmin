package com.sasd13.proadmin.aaa.service;

import com.sasd13.proadmin.aaa.bean.AAAException;
import com.sasd13.proadmin.aaa.bean.Credential;

public interface ICredentialManageService {

	boolean createCredential(Credential credential) throws AAAException;

	boolean updateCredential(Credential credential) throws AAAException;
}

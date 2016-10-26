package com.sasd13.proadmin.aaa.service;

import com.sasd13.proadmin.aaa.AAAException;
import com.sasd13.proadmin.aaa.bean.Credential;

public interface ICredentialReadService {

	boolean containsCredential(Credential credential) throws AAAException;
}

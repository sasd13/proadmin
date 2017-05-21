package com.sasd13.proadmin.aaa.service;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.AuthenticatedUser;

public interface IAuthenticationService {

	AuthenticatedUser logIn(Credential credential);
}

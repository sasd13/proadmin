package com.sasd13.proadmin.aaa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.AuthenticatedUser;
import com.sasd13.proadmin.aaa.dao.IUserDAO;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.service.IAuthenticationService;
import com.sasd13.proadmin.aaa.util.adapter.bean2itf.UserAdapterM2I;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AuthenticationService implements IAuthenticationService {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public AuthenticatedUser logIn(Credential credential) {
		User user = userDAO.find(credential);

		return user != null ? new AuthenticatedUser(new UserAdapterM2I().adapt(user)) : null;
	}
}

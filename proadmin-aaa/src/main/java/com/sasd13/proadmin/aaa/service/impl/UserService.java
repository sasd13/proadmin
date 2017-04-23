package com.sasd13.proadmin.aaa.service.impl;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.User;
import com.sasd13.proadmin.aaa.dao.DAO;
import com.sasd13.proadmin.aaa.dao.IUserDAO;
import com.sasd13.proadmin.aaa.service.IUserService;

public class UserService implements IUserService {

	private IUserDAO userDAO;

	public UserService(DAO dao) {
		userDAO = (IUserDAO) dao.getSession(IUserDAO.class);
	}

	@Override
	public void create(User user, Credential credential) {
		userDAO.create(user, credential);
	}

	@Override
	public void update(User user) {
		userDAO.update(user);
	}

	@Override
	public boolean update(Credential previous, Credential current) {
		return userDAO.update(previous, current);
	}

	@Override
	public User find(Credential credential) {
		return userDAO.find(credential);
	}

	@Override
	public User find(String userID) {
		return userDAO.find(userID);
	}

	@Override
	public List<User> read(Map<String, String[]> parameters) {
		return userDAO.read(parameters);
	}
}

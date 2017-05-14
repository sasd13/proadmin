package com.sasd13.proadmin.aaa.service;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.model.User;

public interface IUserService {

	User create(User user);

	void update(User user);

	User find(Credential credential);

	User find(String userID);

	List<User> read(Map<String, Object> criterias);
}

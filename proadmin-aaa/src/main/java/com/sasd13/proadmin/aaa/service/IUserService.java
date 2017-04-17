package com.sasd13.proadmin.aaa.service;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.bean.user.User;

public interface IUserService {

	void create(User user, Credential credential);

	void update(User user, Credential credential);

	User find(Credential credential);

	User find(String userID);

	List<User> read(Map<String, String[]> parameters);
}

package com.sasd13.proadmin.aaa.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.model.User;
import com.sasd13.proadmin.aaa.model.UserUpdate;

public interface IUserDAO {

	User create(User user);

	void update(UserUpdate userUpdate);

	User find(Credential credential);

	User find(String userID);

	List<User> read(Map<String, Object> criterias);
}

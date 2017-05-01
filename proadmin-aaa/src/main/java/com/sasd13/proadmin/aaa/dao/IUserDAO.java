package com.sasd13.proadmin.aaa.dao;

import java.util.List;
import java.util.Map;

import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.aaa.bean.User;

public interface IUserDAO {

	String TABLE = "users";
	String COLUMN_USERID = "_uid";
	String COLUMN_USERNAME = "_username";
	String COLUMN_PASSWORD = "_password";
	String COLUMN_STATUS = "_status";
	String COLUMN_ROLES = "_roles";
	String COLUMN_INTERMEDIARY = "_intermediary";
	String COLUMN_EMAIL = "_email";

	long create(User user, Credential credential);

	void update(User user);

	boolean update(Credential previous, Credential current);

	User find(Credential credential);

	User find(String userID);

	List<User> read(Map<String, String[]> criterias);
}

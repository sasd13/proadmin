package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.db.IDAO;
import com.sasd13.proadmin.aaa.Credential;

public interface CredentialDAO extends IDAO {

	String TABLE = "credentials";

	String COLUMN_USERNAME = "username";
	String COLUMN_PASSWORD = "password";
	
	boolean insert(Credential credential);
	
	boolean update(Credential credential);
	
	boolean delete(String username);
	
	boolean contains(Credential credential);
}

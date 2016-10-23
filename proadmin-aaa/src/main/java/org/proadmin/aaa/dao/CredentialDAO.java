package org.proadmin.aaa.dao;

import org.proadmin.aaa.Credential;

import com.sasd13.javaex.db.IDAO;

public interface CredentialDAO extends IDAO {

	String TABLE = "credentials";

	String COLUMN_USERNAME = "username";
	String COLUMN_PASSWORD = "password";
	
	boolean insert(Credential credential);
	
	void update(Credential credential);
	
	void delete(String username);
	
	boolean contains(Credential credential);
}

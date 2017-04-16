package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.IReader;
import com.sasd13.javaex.security.Credential;

public interface ICredentialDAO extends IReader<Credential> {

	String TABLE = "credentials";
	String COLUMN_USERNAME = "_username";
	String COLUMN_PASSWORD = "_password";
}

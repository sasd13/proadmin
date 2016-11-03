package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.IChecker;
import com.sasd13.javaex.dao.IDAO;
import com.sasd13.javaex.dao.IManager;
import com.sasd13.javaex.security.Credential;

public interface ICredentialDAO extends IDAO, IManager<Credential>, IChecker<Credential> {

	String TABLE = "credentials";
	String COLUMN_USERNAME = "username";
	String COLUMN_PASSWORD = "password";
}

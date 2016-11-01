package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.ICheckerDAO;
import com.sasd13.javaex.dao.IDAO;
import com.sasd13.javaex.dao.IEntityDAO;
import com.sasd13.javaex.security.Credential;

public interface ICredentialDAO extends IDAO, IEntityDAO<Credential>, ICheckerDAO<Credential> {

	String TABLE = "credentials";
	String COLUMN_USERNAME = "username";
	String COLUMN_PASSWORD = "password";
}

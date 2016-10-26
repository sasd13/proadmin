package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IDAO;
import com.sasd13.proadmin.aaa.bean.Credential;

public interface ICredentialDAO extends IDAO {

	String TABLE = "credentials";
	String COLUMN_USERNAME = "username";
	String COLUMN_PASSWORD = "password";

	long insert(Credential credential) throws DAOException;

	void update(Credential credential) throws DAOException;

	void delete(Credential credential) throws DAOException;

	boolean contains(Credential credential) throws DAOException;
}

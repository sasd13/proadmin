package com.sasd13.proadmin.aaa.dao;

import com.sasd13.javaex.util.conf.AppProperties;
import com.sasd13.proadmin.aaa.util.Names;

public class DAOManager {

	private static final String URL = AppProperties.getProperty(Names.AAA_DB_URL);
	private static final String USERNAME = AppProperties.getProperty(Names.AAA_DB_USERNAME);
	private static final String PASSWORD = AppProperties.getProperty(Names.AAA_DB_PASSWORD);

	public static ICredentialDAO create() {
		return new JDBCCredentialDAO(URL, USERNAME, PASSWORD);
	}
}

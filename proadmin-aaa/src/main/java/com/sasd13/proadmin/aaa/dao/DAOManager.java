package com.sasd13.proadmin.aaa.dao;

import com.sasd13.proadmin.aaa.util.Config;
import com.sasd13.proadmin.aaa.util.Names;

public class DAOManager {

	private static final String URL = Config.getInfo(Names.AAA_DB_URL);
	private static final String USERNAME = Config.getInfo(Names.AAA_DB_USERNAME);
	private static final String PASSWORD = Config.getInfo(Names.AAA_DB_PASSWORD);

	static {
		try {
			Class.forName(Config.getInfo(Names.AAA_DB_DRIVER));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ICredentialDAO create() {
		return new JDBCCredentialDAO(URL, USERNAME, PASSWORD);
	}
}

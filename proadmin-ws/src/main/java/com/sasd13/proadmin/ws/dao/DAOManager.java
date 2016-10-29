package com.sasd13.proadmin.ws.dao;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.JDBCDAO;
import com.sasd13.proadmin.util.Names;

public class DAOManager {

	private static final String URL = AppProperties.getProperty(Names.WS_DB_URL);
	private static final String USERNAME = AppProperties.getProperty(Names.WS_DB_USERNAME);
	private static final String PASSWORD = AppProperties.getProperty(Names.WS_DB_PASSWORD);

	public static DAO create() {
		return new JDBCDAO(URL, USERNAME, PASSWORD);
	}
}

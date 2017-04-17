package com.sasd13.proadmin.ws;

import java.util.Properties;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.proadmin.ws.dao.DAO;
import com.sasd13.proadmin.ws.dao.jdbc.JDBCDAO;

public class DAOManager {

	private static final String URL = AppProperties.getProperty(Names.WS_DB_URL);
	private static final Properties prop = new Properties();

	public static DAO create() {
		if (prop.isEmpty()) {
			prop.setProperty("user", AppProperties.getProperty(Names.WS_DB_USER));
			prop.setProperty("password", AppProperties.getProperty(Names.WS_DB_PASSWORD));
			prop.setProperty("currentSchema", AppProperties.getProperty(Names.WS_DB_SCHEMA));
		}

		return new JDBCDAO(URL, prop);
	}
}

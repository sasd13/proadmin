package com.sasd13.proadmin.aaa.dao;

import java.util.Properties;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.proadmin.aaa.dao.jdbc.JDBCProfileDAO;
import com.sasd13.proadmin.aaa.util.Names;

public class DAOManager {

	private static final String URL = AppProperties.getProperty(Names.AAA_DB_URL);
	private static final Properties prop = new Properties();

	public static IProfileDAO create() {
		if (prop.isEmpty()) {
			prop.setProperty("user", AppProperties.getProperty(Names.AAA_DB_USER));
			prop.setProperty("password", AppProperties.getProperty(Names.AAA_DB_PASSWORD));
		}

		return new JDBCProfileDAO(URL, prop);
	}
}

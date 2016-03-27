package com.sasd13.proadmin.ws.db;

import com.sasd13.proadmin.ws.Config;

public interface JDBCInfo {
	
	String DRIVER = Config.getInfo(Config.DB_DRIVER);
	String URL = Config.getInfo(Config.DB_URL);
	String USERNAME = Config.getInfo(Config.DB_USERNAME);
	String PASSWORD = Config.getInfo(Config.DB_PASSWORD);
}

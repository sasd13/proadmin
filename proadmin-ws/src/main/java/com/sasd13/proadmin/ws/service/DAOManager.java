package com.sasd13.proadmin.ws.service;

import com.sasd13.proadmin.dao.DAO;
import com.sasd13.proadmin.dao.JDBCDAO;
import com.sasd13.proadmin.util.Names;
import com.sasd13.proadmin.ws.Config;

public class DAOManager {

	private static final String URL = Config.getInfo(Names.WS_DB_URL);
	private static final String USERNAME = Config.getInfo(Names.WS_DB_USERNAME);
	private static final String PASSWORD = Config.getInfo(Names.WS_DB_PASSWORD);

	static {
		try {
			Class.forName(Config.getInfo(Names.WS_DB_DRIVER));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DAO create() {
		return new JDBCDAO(URL, USERNAME, PASSWORD);
	}
}

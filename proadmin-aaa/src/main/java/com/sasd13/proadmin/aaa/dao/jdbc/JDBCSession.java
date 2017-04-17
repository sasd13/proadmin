package com.sasd13.proadmin.aaa.dao.jdbc;

import java.sql.Connection;

public abstract class JDBCSession {

	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}

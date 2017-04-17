/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.aaa.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.aaa.dao.DAO;

/**
 *
 * @author Samir
 */
public class JDBCDAO extends DAO {

	private static final Logger LOGGER = Logger.getLogger(JDBCDAO.class);

	private String url;
	private Properties properties;
	private Connection connection;

	public JDBCDAO(String url, Properties properties) {
		super(new JDBCUserDAO());

		this.url = url;
		this.properties = properties;
	}

	@Override
	public void open() throws DAOException {
		try {
			connection = DriverManager.getConnection(url, properties);

			((JDBCSession) userDAO).setConnection(connection);
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOException("Database connection error");
		}
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.warn(e);
			}
		}
	}
}

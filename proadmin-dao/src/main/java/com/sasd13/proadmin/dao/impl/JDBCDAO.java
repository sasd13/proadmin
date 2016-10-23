/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.proadmin.dao.DAO;

/**
 *
 * @author Samir
 */
public class JDBCDAO extends DAO {

	private static String url, username, password;
	
	private Connection connection;

	public JDBCDAO() {
		super(
				new JDBCTeacherDAO(), 
				new JDBCProjectDAO(), 
				new JDBCStudentDAO(), 
				new JDBCTeamDAO(), 
				new JDBCStudentTeamDAO(), 
				new JDBCRunningDAO(), 
				new JDBCRunningTeamDAO(), 
				new JDBCReportDAO()
		);
	}
	
	public static void init(String driver, String url, String username, String password) throws ClassNotFoundException {
		Class.forName(driver);
		
		JDBCDAO.url = url;
		JDBCDAO.username = username;
		JDBCDAO.password = password;
	}

	@Override
	public void open() throws DAOException {
		try {
			connection = DriverManager.getConnection(url, username, password);

			((JDBCEntityDAO<?>) teacherDAO).setConnection(connection);
			((JDBCEntityDAO<?>) projectDAO).setConnection(connection);
			((JDBCEntityDAO<?>) studentDAO).setConnection(connection);
			((JDBCEntityDAO<?>) teamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) studentTeamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) runningDAO).setConnection(connection);
			((JDBCEntityDAO<?>) runningTeamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) reportDAO).setConnection(connection);
		} catch (SQLException e) {
			throw new DAOException("Error connection to database");
		}
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.db;

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
	
	private Connection connection;
	
	static {
		try {
			Class.forName(JDBCInfo.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
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
	
	@Override
	public void open() throws DAOException {
		try {
			connection = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USERNAME, JDBCInfo.PASSWORD);
			
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

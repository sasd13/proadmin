/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.proadmin.dao.member.JDBCStudentDAO;
import com.sasd13.proadmin.dao.member.JDBCStudentTeamDAO;
import com.sasd13.proadmin.dao.member.JDBCTeacherDAO;
import com.sasd13.proadmin.dao.member.JDBCTeamDAO;
import com.sasd13.proadmin.dao.project.JDBCProjectDAO;
import com.sasd13.proadmin.dao.running.JDBCReportDAO;
import com.sasd13.proadmin.dao.running.JDBCRunningDAO;
import com.sasd13.proadmin.dao.running.JDBCRunningTeamDAO;

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

			((JDBCEntityDAO<?>) iTeacherDAO).setConnection(connection);
			((JDBCEntityDAO<?>) iProjectDAO).setConnection(connection);
			((JDBCEntityDAO<?>) iStudentDAO).setConnection(connection);
			((JDBCEntityDAO<?>) iTeamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) iStudentTeamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) iRunningDAO).setConnection(connection);
			((JDBCEntityDAO<?>) iRunningTeamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) iReportDAO).setConnection(connection);
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

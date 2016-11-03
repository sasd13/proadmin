/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
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

	private static final Logger LOG = Logger.getLogger(JDBCDAO.class);

	private String url, username, password;
	private Connection connection;

	public JDBCDAO(String url, String username, String password) {
		super(
				new JDBCTeacherDAO(), 
				new JDBCProjectDAO(), 
				new JDBCStudentDAO(), 
				new JDBCTeamDAO(), 
				new JDBCStudentTeamDAO(), 
				new JDBCRunningDAO(), 
				new JDBCAcademicLevelDAO(), 
				new JDBCRunningTeamDAO(), 
				new JDBCReportDAO()
		);

		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	public void open() throws DAOException {
		try {
			connection = DriverManager.getConnection(url, username, password);

			((JDBCSession<?>) teacherDAO).setConnection(connection);
			((JDBCSession<?>) projectDAO).setConnection(connection);
			((JDBCSession<?>) studentDAO).setConnection(connection);
			((JDBCSession<?>) teamDAO).setConnection(connection);
			((JDBCSession<?>) studentTeamDAO).setConnection(connection);
			((JDBCSession<?>) runningDAO).setConnection(connection);
			((JDBCSession<?>) academicLevelDAO).setConnection(connection);
			((JDBCSession<?>) runningTeamDAO).setConnection(connection);
			((JDBCSession<?>) reportDAO).setConnection(connection);
		} catch (SQLException e) {
			LOG.error(e);
			throw new DAOException("Database connection error");
		}
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.warn(e);
			}
		}
	}
}

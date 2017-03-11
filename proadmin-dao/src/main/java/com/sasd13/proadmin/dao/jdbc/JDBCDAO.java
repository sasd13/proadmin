/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.proadmin.dao.DAO;

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
		super(
				new JDBCTeacherDAO(), 
				new JDBCProjectDAO(), 
				new JDBCStudentDAO(), 
				new JDBCTeamDAO(), 
				new JDBCStudentTeamDAO(), 
				new JDBCRunningDAO(), 
				new JDBCAcademicLevelDAO(), 
				new JDBCRunningTeamDAO(), 
				new JDBCReportDAO(),
				new JDBCLeadEvaluationDAO(),
				new JDBCIndividualEvaluationDAO()
		);

		this.url = url;
		this.properties = properties;
	}

	@Override
	public void open() {
		try {
			connection = DriverManager.getConnection(url, properties);

			((JDBCSession<?>) teacherDAO).setConnection(connection);
			((JDBCSession<?>) projectDAO).setConnection(connection);
			((JDBCSession<?>) studentDAO).setConnection(connection);
			((JDBCSession<?>) teamDAO).setConnection(connection);
			((JDBCSession<?>) studentTeamDAO).setConnection(connection);
			((JDBCSession<?>) runningDAO).setConnection(connection);
			((JDBCSession<?>) academicLevelDAO).setConnection(connection);
			((JDBCSession<?>) runningTeamDAO).setConnection(connection);
			((JDBCSession<?>) reportDAO).setConnection(connection);
			((JDBCSession<?>) leadEvaluationDAO).setConnection(connection);
			((JDBCSession<?>) individualEvaluationDAO).setConnection(connection);
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

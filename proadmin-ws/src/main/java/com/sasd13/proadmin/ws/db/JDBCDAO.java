/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.sasd13.javaex.db.DBException;
import com.sasd13.proadmin.dao.DAO;

/**
 *
 * @author Samir
 */
public class JDBCDAO extends DAO {
	
	private static class JDBCDAOHolder {
		private static final JDBCDAO INSTANCE = new JDBCDAO();
	}
	
	private Connection connection;
	
	private JDBCDAO() {
		super(
			new JDBCTeacherDAO(),
			new JDBCProjectDAO(),
			new JDBCStudentDAO(),
			new JDBCTeamDAO(),
			new JDBCStudentTeamDAO(),
			new JDBCRunningDAO(),
			new JDBCRunningTeamDAO(),
			new JDBCReportDAO(),
			new JDBCLeadEvaluationDAO(),
			new JDBCIndividualEvaluationDAO()
		);
		
		try {
			Class.forName(JDBCInformation.DRIVER);
			
			open();
			createTablesIfNotExist();
		} catch (ClassNotFoundException | DBException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static JDBCDAO getInstance() {
		return JDBCDAOHolder.INSTANCE;
	}
	
	@Override
	public void open() throws DBException {
		try {
			connection = DriverManager.getConnection(JDBCInformation.URL, JDBCInformation.USER, JDBCInformation.PASSWORD);
			
			((JDBCEntityDAO<?>) teacherDAO).setConnection(connection);
			((JDBCEntityDAO<?>) projectDAO).setConnection(connection);
			((JDBCEntityDAO<?>) studentDAO).setConnection(connection);
			((JDBCEntityDAO<?>) teamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) studentTeamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) runningDAO).setConnection(connection);
			((JDBCEntityDAO<?>) runningTeamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) reportDAO).setConnection(connection);
			((JDBCEntityDAO<?>) leadEvaluationDAO).setConnection(connection);
			((JDBCEntityDAO<?>) individualEvaluationDAO).setConnection(connection);
		} catch (SQLException e) {
			throw new DBException("Database access error");
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
	
	private void createTablesIfNotExist() {
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			statement.executeUpdate(DBHandler.TEACHER_TABLE_CREATE);
			statement.executeUpdate(DBHandler.PROJECT_TABLE_CREATE);
			statement.executeUpdate(DBHandler.STUDENT_TABLE_CREATE);
			statement.executeUpdate(DBHandler.TEAM_TABLE_CREATE);
			statement.executeUpdate(DBHandler.STUDENTTEAM_TABLE_CREATE);
			statement.executeUpdate(DBHandler.RUNNING_TABLE_CREATE);
			statement.executeUpdate(DBHandler.RUNNINGTEAM_TABLE_CREATE);
			statement.executeUpdate(DBHandler.REPORT_TABLE_CREATE);
			statement.executeUpdate(DBHandler.LEADEVALUATION_TABLE_CREATE);
			statement.executeUpdate(DBHandler.INDIVIDUALEVALUATION_TABLE_CREATE);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

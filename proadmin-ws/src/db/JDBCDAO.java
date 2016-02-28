/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.sasd13.proadmin.core.db.DAO;

/**
 *
 * @author Samir
 */
public class JDBCDAO extends DAO {
	
	private static JDBCDAO instance = null;
	
	private Connection connection;
	
	private JDBCDAO() {
		super(
			new JDBCTeacherDAO(),
			new JDBCProjectDAO(),
			new JDBCRunningDAO(),
			new JDBCTeamDAO(),
			new JDBCStudentDAO(),
			new JDBCStudentTeamDAO(),
			new JDBCReportDAO(),
			new JDBCLeadEvaluationDAO(),
			new JDBCIndividualEvaluationDAO()
		);
		
		try {
			Class.forName(JDBCInformation.DRIVER);
			
			open();
			createTablesIfNotExist();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}
	
	public static JDBCDAO getInstance() {
		if (instance == null) {
			instance = new JDBCDAO();
		}
		
		return instance;
	}
	
	@Override
	public void open() {
		try {
			connection = DriverManager.getConnection(JDBCInformation.URL, JDBCInformation.USER, JDBCInformation.PASSWORD);
			
			((JDBCEntityDAO<?>) teacherDAO).setConnection(connection);
			((JDBCEntityDAO<?>) projectDAO).setConnection(connection);
			((JDBCEntityDAO<?>) runningDAO).setConnection(connection);
			((JDBCEntityDAO<?>) teamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) studentDAO).setConnection(connection);
			((JDBCEntityDAO<?>) studentTeamDAO).setConnection(connection);
			((JDBCEntityDAO<?>) reportDAO).setConnection(connection);
			((JDBCEntityDAO<?>) leadEvaluationDAO).setConnection(connection);
			((JDBCEntityDAO<?>) individualEvaluationDAO).setConnection(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createTablesIfNotExist() {
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			statement.executeUpdate(DBHandler.TEACHER_TABLE_CREATE);
			statement.executeUpdate(DBHandler.PROJECT_TABLE_CREATE);
			statement.executeUpdate(DBHandler.RUNNING_TABLE_CREATE);
			statement.executeUpdate(DBHandler.TEAM_TABLE_CREATE);
			statement.executeUpdate(DBHandler.STUDENT_TABLE_CREATE);
			statement.executeUpdate(DBHandler.STUDENTTEAM_TABLE_CREATE);
			statement.executeUpdate(DBHandler.REPORT_TABLE_CREATE);
			statement.executeUpdate(DBHandler.LEADEVALUATION_TABLE_CREATE);
			statement.executeUpdate(DBHandler.INDIVIDUALEVALUATION_TABLE_CREATE);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

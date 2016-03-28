/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.ReportDAO;
import com.sasd13.proadmin.dao.util.SQLWhereClauseParser;

/**
 *
 * @author Samir
 */
public class JDBCReportDAO extends JDBCEntityDAO<Report> implements ReportDAO {
	
	private JDBCLeadEvaluationDAO leadEvaluationDAO;
	private JDBCIndividualEvaluationDAO individualEvaluationDAO;
	
	public JDBCReportDAO() {
		leadEvaluationDAO = new JDBCLeadEvaluationDAO();
		individualEvaluationDAO = new JDBCIndividualEvaluationDAO();
	}
	
	@Override
	public LeadEvaluationDAO getLeadEvaluationDAO() {
		return leadEvaluationDAO;
	}
	
	@Override
	public IndividualEvaluationDAO getIndividualEvaluationDAO() {
		return individualEvaluationDAO;
	}
	
	@Override
	public void setConnection(Connection connection) {
		super.setConnection(connection);
		
		leadEvaluationDAO.setConnection(connection);
		individualEvaluationDAO.setConnection(connection);
	}
	
	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Report report) throws SQLException {
		preparedStatement.setString(1, String.valueOf(report.getMeetingDate()));
		preparedStatement.setInt(2, report.getSessionNumber());
		preparedStatement.setString(3, report.getComment());
		preparedStatement.setLong(4, report.getRunningTeam().getId());
	}
	
	@Override
	protected Report getResultSetValues(ResultSet resultSet) throws SQLException {
		RunningTeam runningTeam = new RunningTeam();
		runningTeam.setId(resultSet.getLong(COLUMN_RUNNINGTEAM));
		
		Report report = new Report(runningTeam);
		report.setId(resultSet.getLong(COLUMN_ID));
		report.setMeetingDate(Timestamp.valueOf(resultSet.getString(COLUMN_MEETINGDATE)));
		report.setSessionNumber(resultSet.getInt(COLUMN_SESSIONNUMBER));
		report.setComment(resultSet.getString(COLUMN_COMMENT));
		
		return report;
	}
	
	@Override
	public long insert(Report report) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_MEETINGDATE + ", "
					+ COLUMN_SESSIONNUMBER + ", " 
					+ COLUMN_COMMENT + ", " 
					+ COLUMN_RUNNINGTEAM
				+ ") VALUES (?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, report);
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
				report.setId(id);
				
				leadEvaluationDAO.insert(report.getLeadEvaluation());
				
				for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
					individualEvaluationDAO.insert(individualEvaluation);
				}
			} else {
				throw new SQLException("Report not inserted: " + report);
			}
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return id;
	}
	
	@Override
	public void update(Report report) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_MEETINGDATE + " = ?, " 
					+ COLUMN_SESSIONNUMBER + " = ?, " 
					+ COLUMN_COMMENT + " = ?, " 
					+ COLUMN_RUNNINGTEAM + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, report);
			preparedStatement.setLong(6, report.getId());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			leadEvaluationDAO.update(report.getLeadEvaluation());
			for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
				individualEvaluationDAO.update(individualEvaluation);
			}
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void delete(Report report) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			connection.setAutoCommit(false);
			
			leadEvaluationDAO.delete(report.getLeadEvaluation());
			
			for (IndividualEvaluation individualEvaluation : report.getIndividualEvaluations()) {
				individualEvaluationDAO.delete(individualEvaluation);
			}
			
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, report.getId());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public Report select(long id) {
		Report report = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ? AND "
					+ COLUMN_DELETED + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			preparedStatement.setBoolean(2, false);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				report = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return report;
	}
	
	public List<Report> select(Map<String, String[]> parameters) {
		List<Report> reports = new ArrayList<>();
		
		Statement statement = null;
		
		try {			
			String query = "SELECT * FROM " + TABLE
					+ " WHERE " 
						+ SQLWhereClauseParser.parse(parameters, ReportDAO.class) + " AND "
						+ COLUMN_DELETED + " = false";
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				reports.add(getResultSetValues(resultSet));
			}
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
		
		return reports;
	}
	
	@Override
	public List<Report> selectAll() {
		List<Report> reports = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE
				+ " WHERE " 
					+ COLUMN_DELETED + " = false";
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				reports.add(getResultSetValues(resultSet));
			}
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
		
		return reports;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

/**
 *
 * @author Samir
 */
public class JDBCLeadEvaluationDAO extends JDBCEntityDAO<LeadEvaluation> implements LeadEvaluationDAO {
	
	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, LeadEvaluation leadEvaluation) throws SQLException {
		preparedStatement.setFloat(1, leadEvaluation.getPlanningMark());
		preparedStatement.setString(2, leadEvaluation.getPlanningComment());
		preparedStatement.setFloat(3, leadEvaluation.getCommunicationMark());
		preparedStatement.setString(4, leadEvaluation.getCommunicationComment());
		preparedStatement.setLong(5, leadEvaluation.getReport().getId());
		preparedStatement.setLong(6, leadEvaluation.getStudent().getId());
	}
	
	@Override
	protected LeadEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();
		report.setId(resultSet.getLong(COLUMN_REPORT_ID));
		
		LeadEvaluation leadEvaluation = new LeadEvaluation(report);
		leadEvaluation.setId(resultSet.getLong(COLUMN_ID));
		leadEvaluation.setPlanningMark(resultSet.getFloat(COLUMN_PLANNINGMARK));
		leadEvaluation.setPlanningComment(resultSet.getString(COLUMN_PLANNINGCOMMENT));
		leadEvaluation.setCommunicationMark(resultSet.getFloat(COLUMN_COMMUNICATIONMARK));
		leadEvaluation.setCommunicationComment(resultSet.getString(COLUMN_COMMUNICATIONCOMMENT));
		
		Student student = new Student();
		student.setId(resultSet.getLong(COLUMN_STUDENT_ID));
		leadEvaluation.setStudent(student);
		
		return leadEvaluation;
	}
	
	@Override
	public long insert(LeadEvaluation leadEvaluation) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_PLANNINGMARK + ", " 
					+ COLUMN_PLANNINGCOMMENT + ", " 
					+ COLUMN_COMMUNICATIONMARK + ", " 
					+ COLUMN_COMMUNICATIONCOMMENT + ", " 
					+ COLUMN_REPORT_ID + ", " 
					+ COLUMN_STUDENT_ID
				+ ") VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, leadEvaluation);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
			}
			
			leadEvaluation.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	@Override
	public void update(LeadEvaluation leadEvaluation) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_PLANNINGMARK + " = ?, " 
					+ COLUMN_PLANNINGCOMMENT + " = ?, " 
					+ COLUMN_COMMUNICATIONMARK + " = ?, " 
					+ COLUMN_COMMUNICATIONCOMMENT + " = ?, " 
					+ COLUMN_REPORT_ID + " = ?, " 
					+ COLUMN_STUDENT_ID + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, leadEvaluation);
			
			preparedStatement.setLong(7, leadEvaluation.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void delete(long id) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public LeadEvaluation select(long id) {
		LeadEvaluation leadEvaluation = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (!resultSet.getBoolean(COLUMN_DELETED)) {
					leadEvaluation = getResultSetValues(resultSet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return leadEvaluation;
	}
	
	public List<LeadEvaluation> select(Map<String, String[]> parameters) {
		List<LeadEvaluation> leadEvaluations = new ArrayList<>();
		
		Statement statement = null;
		
		try {			
			String query = "SELECT * FROM " + TABLE
					+ " WHERE " + WhereClauseParser.parse(LeadEvaluationDAO.class, parameters) + ";";
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(leadEvaluations, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return leadEvaluations;
	}
	
	private void fillListWithResultSet(List<LeadEvaluation> leadEvaluations, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			if (!resultSet.getBoolean(COLUMN_DELETED)) {
				leadEvaluations.add(getResultSetValues(resultSet));
			}
		}
	}
	
	@Override
	public List<LeadEvaluation> selectAll() {
		List<LeadEvaluation> leadEvaluations = new ArrayList<LeadEvaluation>();
		
		String query = "SELECT * FROM " + TABLE;
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(leadEvaluations, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return leadEvaluations;
	}
}

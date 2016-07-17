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

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.condition.ConditionBuilder;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.LeadEvaluationDAO;
import com.sasd13.proadmin.dao.condition.LeadEvaluationConditionExpression;

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
		preparedStatement.setLong(5, leadEvaluation.getStudent().getId());
		preparedStatement.setLong(6, leadEvaluation.getReport().getId());
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
	public long insert(LeadEvaluation leadEvaluation) throws DAOException {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_PLANNINGMARK + ", " 
					+ COLUMN_PLANNINGCOMMENT + ", " 
					+ COLUMN_COMMUNICATIONMARK + ", " 
					+ COLUMN_COMMUNICATIONCOMMENT + ", " 
					+ COLUMN_STUDENT_ID + ", " 
					+ COLUMN_REPORT_ID 
				+ ") VALUES (?, ?, ?, ?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, leadEvaluation);
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
				leadEvaluation.setId(id);
			} else {
				throw new SQLException("LeadEvaluation not inserted: " + leadEvaluation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("LeadEvaluation not inserted: " + leadEvaluation);
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
	public void update(LeadEvaluation leadEvaluation) throws DAOException {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_PLANNINGMARK + " = ?, " 
					+ COLUMN_PLANNINGCOMMENT + " = ?, " 
					+ COLUMN_COMMUNICATIONMARK + " = ?, " 
					+ COLUMN_COMMUNICATIONCOMMENT + " = ?, " 
					+ COLUMN_STUDENT_ID + " = ?, " 
					+ COLUMN_REPORT_ID + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, leadEvaluation);
			preparedStatement.setLong(7, leadEvaluation.getId());
			
			preparedStatement.executeUpdate();
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("LeadEvaluation not updated: id=" + leadEvaluation.getId());
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
	public void delete(LeadEvaluation leadEvaluation) throws DAOException {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, leadEvaluation.getId());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("LeadEvaluation not deleted: id=" + leadEvaluation.getId());
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
	public LeadEvaluation select(long id) {
		LeadEvaluation leadEvaluation = null;
		
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
				leadEvaluation = getResultSetValues(resultSet);
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
		
		return leadEvaluation;
	}
	
	public List<LeadEvaluation> select(Map<String, String[]> parameters) {
		List<LeadEvaluation> leadEvaluations = new ArrayList<>();
		
		Statement statement = null;
		
		try {			
			String query = "SELECT * FROM " + TABLE
					+ " WHERE " 
						+ ConditionBuilder.parse(parameters, LeadEvaluationConditionExpression.class) + " AND "
						+ COLUMN_DELETED + " = false";
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				leadEvaluations.add(getResultSetValues(resultSet));
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
		
		return leadEvaluations;
	}
	
	@Override
	public List<LeadEvaluation> selectAll() {
		List<LeadEvaluation> leadEvaluations = new ArrayList<LeadEvaluation>();
		
		String query = "SELECT * FROM " + TABLE
				+ " WHERE " 
					+ COLUMN_DELETED + " = false";
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				leadEvaluations.add(getResultSetValues(resultSet));
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
		
		return leadEvaluations;
	}
}

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
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.IndividualEvaluationDAO;
import com.sasd13.proadmin.dao.util.SQLWhereClauseParser;

/**
 *
 * @author Samir
 */
public class JDBCIndividualEvaluationDAO extends JDBCEntityDAO<IndividualEvaluation> implements IndividualEvaluationDAO {
	
	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		preparedStatement.setFloat(1, individualEvaluation.getMark());
		preparedStatement.setLong(2, individualEvaluation.getStudent().getId());
		preparedStatement.setLong(3, individualEvaluation.getReport().getId());
	}
	
	@Override
	protected IndividualEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();
		report.setId(resultSet.getLong(COLUMN_REPORT_ID));
		
		IndividualEvaluation individualEvaluation = new IndividualEvaluation(report);
		individualEvaluation.setId(resultSet.getLong(COLUMN_ID));
		individualEvaluation.setMark(resultSet.getFloat(COLUMN_MARK));		
		
		Student student = new Student();
		student.setId(resultSet.getLong(COLUMN_STUDENT_ID));
		individualEvaluation.setStudent(student);
		
		return individualEvaluation;
	}
	
	@Override
	public long insert(IndividualEvaluation individualEvaluation) throws DAOException {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_MARK + ", "
					+ COLUMN_STUDENT_ID + ", "
					+ COLUMN_REPORT_ID
				+ ") VALUES (?, ?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, individualEvaluation);
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
				individualEvaluation.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("IndividualEvaluation not inserted: " + individualEvaluation);
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
	public void update(IndividualEvaluation individualEvaluation) throws DAOException {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_MARK + " = ?, " 
					+ COLUMN_STUDENT_ID + " = ?, "
					+ COLUMN_REPORT_ID + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, individualEvaluation);
			preparedStatement.setLong(4, individualEvaluation.getId());
			
			preparedStatement.executeUpdate();
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("IndividualEvaluation not updated: id=" + individualEvaluation.getId());
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
	public void delete(IndividualEvaluation individualEvaluation) throws DAOException {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, individualEvaluation.getId());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("IndividualEvaluation not deleted: id=" + individualEvaluation.getId());
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
	public IndividualEvaluation select(long id) {
		IndividualEvaluation individualEvaluation = null;
		
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
				individualEvaluation = getResultSetValues(resultSet);
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
		
		return individualEvaluation;
	}
	
	public List<IndividualEvaluation> select(Map<String, String[]> parameters) {
		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
		
		Statement statement = null;
		
		try {			
			String query = "SELECT * FROM " + TABLE
					+ " WHERE " 
						+ SQLWhereClauseParser.parse(IndividualEvaluationDAO.class, parameters) + " AND "
						+ COLUMN_DELETED + " = false";
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				individualEvaluations.add(getResultSetValues(resultSet));
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
		
		return individualEvaluations;
	}
	
	@Override
	public List<IndividualEvaluation> selectAll() {
		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE
				+ " WHERE " 
					+ COLUMN_DELETED + " = false";
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				individualEvaluations.add(getResultSetValues(resultSet));
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
		
		return individualEvaluations;
	}
}

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

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_PLANNINGMARK);
		builder.append(", " + COLUMN_PLANNINGCOMMENT);
		builder.append(", " + COLUMN_COMMUNICATIONMARK);
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT);
		builder.append(", " + COLUMN_STUDENT_ID);
		builder.append(", " + COLUMN_REPORT_ID);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
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
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_PLANNINGMARK + " = ?");
		builder.append(", " + COLUMN_PLANNINGCOMMENT + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONMARK + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT + " = ?");
		builder.append(", " + COLUMN_STUDENT_ID + " = ?");
		builder.append(", " + COLUMN_REPORT_ID + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
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
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DELETED + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
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

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");
		builder.append(" AND ");
		builder.append(COLUMN_DELETED + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
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
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM ");
			builder.append(TABLE);
			builder.append(" WHERE ");
			builder.append(ConditionBuilder.parse(parameters, LeadEvaluationConditionExpression.class));
			builder.append(" AND ");
			builder.append(COLUMN_DELETED + " = ?");

			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
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

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_DELETED + " = ?");

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
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

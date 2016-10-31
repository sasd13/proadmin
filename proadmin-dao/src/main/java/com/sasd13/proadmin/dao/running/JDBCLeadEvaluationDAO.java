/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.running;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.ConditionBuilder;
import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.LeadEvaluation;
import com.sasd13.proadmin.bean.running.Report;

/**
 *
 * @author Samir
 */
public class JDBCLeadEvaluationDAO extends JDBCEntityDAO<LeadEvaluation> implements ILeadEvaluationDAO {

	private static final Logger LOG = Logger.getLogger(JDBCLeadEvaluationDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCLeadEvaluationDAO() {
		expressionBuilder = new LeadEvaluationExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, LeadEvaluation leadEvaluation) throws SQLException {
		preparedStatement.setFloat(1, leadEvaluation.getPlanningMark());
		preparedStatement.setString(2, leadEvaluation.getPlanningComment());
		preparedStatement.setFloat(3, leadEvaluation.getCommunicationMark());
		preparedStatement.setString(4, leadEvaluation.getCommunicationComment());
		preparedStatement.setString(5, leadEvaluation.getReport().getNumber());
		preparedStatement.setString(6, leadEvaluation.getStudent().getNumber());
	}

	@Override
	protected LeadEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();
		report.setNumber(resultSet.getString(COLUMN_REPORT_CODE));

		Student student = new Student();
		student.setNumber(resultSet.getString(COLUMN_STUDENT_CODE));

		LeadEvaluation leadEvaluation = new LeadEvaluation(report);
		leadEvaluation.setPlanningMark(resultSet.getFloat(COLUMN_PLANNINGMARK));
		leadEvaluation.setPlanningComment(resultSet.getString(COLUMN_PLANNINGCOMMENT));
		leadEvaluation.setCommunicationMark(resultSet.getFloat(COLUMN_COMMUNICATIONMARK));
		leadEvaluation.setCommunicationComment(resultSet.getString(COLUMN_COMMUNICATIONCOMMENT));
		leadEvaluation.setStudent(student);

		return leadEvaluation;
	}

	@Override
	public long insert(LeadEvaluation leadEvaluation) throws DAOException {
		LOG.info("insert : reportNumber=" + leadEvaluation.getReport().getNumber() + ", studentNumber=" + leadEvaluation.getStudent().getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_PLANNINGMARK);
		builder.append(", " + COLUMN_PLANNINGCOMMENT);
		builder.append(", " + COLUMN_COMMUNICATIONMARK);
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT);
		builder.append(", " + COLUMN_REPORT_CODE);
		builder.append(", " + COLUMN_STUDENT_CODE);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, leadEvaluation);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "insert failed", "LeadEvaluation not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(LeadEvaluation leadEvaluation) throws DAOException {
		LOG.info("update : reportNumber=" + leadEvaluation.getReport().getNumber() + ", studentNumber=" + leadEvaluation.getStudent().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_PLANNINGMARK + " = ?");
		builder.append(", " + COLUMN_PLANNINGCOMMENT + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONMARK + " = ?");
		builder.append(", " + COLUMN_COMMUNICATIONCOMMENT + " = ?");
		builder.append(", " + COLUMN_STUDENT_CODE + " = ?");
		builder.append(", " + COLUMN_REPORT_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, leadEvaluation);
			preparedStatement.setString(7, leadEvaluation.getReport().getNumber());
			preparedStatement.setString(8, leadEvaluation.getStudent().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "update failed", "LeadEvaluation not updated");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(LeadEvaluation leadEvaluation) throws DAOException {
		LOG.info("delete : reportNumber=" + leadEvaluation.getReport().getNumber() + ", studentNumber=" + leadEvaluation.getStudent().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, leadEvaluation.getReport().getNumber());
			preparedStatement.setString(2, leadEvaluation.getStudent().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "delete failed", "LeadEvaluation not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public LeadEvaluation select(long id) throws DAOException {
		LOG.info("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<LeadEvaluation> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("select : parameters=" + URLQueryUtils.toString(parameters));

		List<LeadEvaluation> leadEvaluations = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");

		try {
			builder.append(ConditionBuilder.parse(parameters, expressionBuilder));
		} catch (ConditionException e) {
			doCatchWithThrow(LOG, "select failed", e.getMessage());
		}

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				leadEvaluations.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "select failed", "LeadEvaluations not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return leadEvaluations;
	}

	@Override
	public List<LeadEvaluation> selectAll() throws DAOException {
		LOG.info("selectAll");

		List<LeadEvaluation> leadEvaluations = new ArrayList<LeadEvaluation>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				leadEvaluations.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "selectAll failed", "LeadEvaluations not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return leadEvaluations;
	}
}

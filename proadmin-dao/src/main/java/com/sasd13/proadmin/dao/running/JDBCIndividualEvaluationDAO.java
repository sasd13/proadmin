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

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.condition.ConditionBuilder;
import com.sasd13.javaex.net.http.URLQueryEncoder;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;
import com.sasd13.proadmin.dao.JDBCEntityDAO;

/**
 *
 * @author Samir
 */
public class JDBCIndividualEvaluationDAO extends JDBCEntityDAO<IndividualEvaluation> implements IIndividualEvaluationDAO {

	private static final Logger LOG = Logger.getLogger(JDBCIndividualEvaluationDAO.class);

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
		LOG.info("JDBCIndividualEvaluationDAO --> insert : studentNumber=" + individualEvaluation.getStudent().getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_MARK);
		builder.append(", " + COLUMN_STUDENT_ID);
		builder.append(", " + COLUMN_REPORT_ID);
		builder.append(") VALUES (?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, individualEvaluation);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
				individualEvaluation.setId(id);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCIndividualEvaluationDAO --> insert failed", "IndividualEvaluation not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(IndividualEvaluation individualEvaluation) throws DAOException {
		LOG.info("JDBCIndividualEvaluationDAO --> update : studentNumber=" + individualEvaluation.getStudent().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_MARK + " = ?");
		builder.append(", " + COLUMN_STUDENT_ID + " = ?");
		builder.append(", " + COLUMN_REPORT_ID + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, individualEvaluation);
			preparedStatement.setLong(4, individualEvaluation.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCIndividualEvaluationDAO --> update failed", "IndividualEvaluation not updated");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) throws DAOException {
		LOG.info("JDBCIndividualEvaluationDAO --> delete : studentNumber=" + individualEvaluation.getStudent().getNumber());

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
			preparedStatement.setLong(2, individualEvaluation.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCIndividualEvaluationDAO --> delete failed", "IndividualEvaluation not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public IndividualEvaluation select(long id) throws DAOException {
		LOG.info("JDBCIndividualEvaluationDAO --> select : id=" + id);

		IndividualEvaluation individualEvaluation = null;

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
				individualEvaluation = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCIndividualEvaluationDAO --> select failed", "IndividualEvaluation not selected");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return individualEvaluation;
	}

	public List<IndividualEvaluation> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCIndividualEvaluationDAO --> select : parameters=" + URLQueryEncoder.toString(parameters));

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		Statement statement = null;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM ");
			builder.append(TABLE);
			builder.append(" WHERE ");
			builder.append(ConditionBuilder.parse(parameters, new IndividualEvaluationExpressionBuilder()));
			builder.append(" AND ");
			builder.append(COLUMN_DELETED + " = false");

			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				individualEvaluations.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCIndividualEvaluationDAO --> select failed", "IndividualEvaluations not selected");
		} finally {
			doFinally(statement, LOG);
		}

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> selectAll() throws DAOException {
		LOG.info("JDBCIndividualEvaluationDAO --> selectAll");

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_DELETED + " = false");

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				individualEvaluations.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCIndividualEvaluationDAO --> selectAll failed", "IndividualEvaluations not selected");
		} finally {
			doFinally(statement, LOG);
		}

		return individualEvaluations;
	}
}

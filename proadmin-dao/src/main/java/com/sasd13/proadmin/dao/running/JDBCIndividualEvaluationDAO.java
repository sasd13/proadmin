/*
 * To change this license header, choose License Headers in individualEvaluation Properties.
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
import com.sasd13.proadmin.bean.running.IndividualEvaluation;
import com.sasd13.proadmin.bean.running.Report;

/**
 *
 * @author Samir
 */
public class JDBCIndividualEvaluationDAO extends JDBCEntityDAO<IndividualEvaluation> implements IIndividualEvaluationDAO {

	private static final Logger LOG = Logger.getLogger(JDBCIndividualEvaluationDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCIndividualEvaluationDAO() {
		expressionBuilder = new IndividualEvaluationExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, IndividualEvaluation individualEvaluation) throws SQLException {
		preparedStatement.setFloat(1, individualEvaluation.getMark());
		preparedStatement.setString(2, individualEvaluation.getReport().getNumber());
		preparedStatement.setString(3, individualEvaluation.getStudent().getNumber());
	}

	@Override
	protected IndividualEvaluation getResultSetValues(ResultSet resultSet) throws SQLException {
		Report report = new Report();
		report.setNumber(resultSet.getString(COLUMN_REPORT_CODE));

		Student student = new Student();
		student.setNumber(resultSet.getString(COLUMN_STUDENT_CODE));

		IndividualEvaluation individualEvaluation = new IndividualEvaluation(report);
		individualEvaluation.setMark(resultSet.getFloat(COLUMN_MARK));
		individualEvaluation.setStudent(student);

		return individualEvaluation;
	}

	@Override
	public long insert(IndividualEvaluation individualEvaluation) throws DAOException {
		LOG.info("insert : reportNumber=" + individualEvaluation.getReport().getNumber() + ", studentNumber=" + individualEvaluation.getStudent().getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_MARK);
		builder.append(", " + COLUMN_REPORT_CODE);
		builder.append(", " + COLUMN_STUDENT_CODE);
		builder.append(") VALUES (?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, individualEvaluation);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatchWithThrow(e, "IndividualEvaluation not inserted", LOG);
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(IndividualEvaluation individualEvaluation) throws DAOException {
		LOG.info("update : reportNumber=" + individualEvaluation.getReport().getNumber() + ", studentNumber=" + individualEvaluation.getStudent().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_MARK + " = ?");
		builder.append(", " + COLUMN_REPORT_CODE + " = ?");
		builder.append(", " + COLUMN_STUDENT_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, individualEvaluation);
			preparedStatement.setString(4, individualEvaluation.getReport().getNumber());
			preparedStatement.setString(5, individualEvaluation.getStudent().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(e, "IndividualEvaluation not updated", LOG);
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(IndividualEvaluation individualEvaluation) throws DAOException {
		LOG.info("delete : reportNumber=" + individualEvaluation.getReport().getNumber() + ", studentNumber=" + individualEvaluation.getStudent().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_REPORT_CODE + " = ?");
		builder.append(" AND " + COLUMN_STUDENT_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, individualEvaluation.getReport().getNumber());
			preparedStatement.setString(2, individualEvaluation.getStudent().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(e, "IndividualEvaluation not deleted", LOG);
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public IndividualEvaluation select(long id) throws DAOException {
		LOG.info("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<IndividualEvaluation> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("select : parameters=" + URLQueryUtils.toString(parameters));

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");

		try {
			builder.append(ConditionBuilder.parse(parameters, expressionBuilder));
		} catch (ConditionException e) {
			doCatchWithThrow(e, "Parsing error", LOG);
		}

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				individualEvaluations.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(e, "IndividualEvaluations not readed", LOG);
		} finally {
			doFinally(statement, LOG);
		}

		return individualEvaluations;
	}

	@Override
	public List<IndividualEvaluation> selectAll() throws DAOException {
		LOG.info("selectAll");

		List<IndividualEvaluation> individualEvaluations = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				individualEvaluations.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(e, "IndividualEvaluations not readed", LOG);
		} finally {
			doFinally(statement, LOG);
		}

		return individualEvaluations;
	}
}

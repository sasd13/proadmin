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
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.condition.ConditionBuilder;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.dao.validator.ValidatorUtils;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCEntityDAO<Running> implements IRunningDAO {

	private static final Logger LOG = Logger.getLogger(JDBCRunningDAO.class);

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setString(2, running.getProject().getCode());
		preparedStatement.setString(3, running.getTeacher().getNumber());
	}

	@Override
	protected Running getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_PROJECT_CODE));
		
		Teacher teacher = new Teacher();
		teacher.setNumber(resultSet.getString(COLUMN_TEACHER_CODE));
		
		Running running = new Running(project);
		running.setYear(resultSet.getInt(COLUMN_YEAR));
		running.setTeacher(teacher);

		return running;
	}

	@Override
	public long insert(Running running) throws DAOException {
		ValidatorUtils.validate(running);
		
		LOG.info("JDBCRunningDAO --> insert : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_YEAR);
		builder.append(", " + COLUMN_PROJECT_CODE);
		builder.append(", " + COLUMN_TEACHER_CODE);
		builder.append(") VALUES (?, ?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, running);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCRunningDAO --> insert failed", "Running not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(Running running) throws DAOException {
		ValidatorUtils.validate(running);
		
		LOG.info("JDBCRunningDAO --> update : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(", " + COLUMN_PROJECT_CODE + " = ?");
		builder.append(", " + COLUMN_TEACHER_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEACHER_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, running);
			preparedStatement.setString(4, running.getProject().getCode());
			preparedStatement.setString(5, running.getTeacher().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCRunningDAO --> update failed", "Running not updated");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(Running running) throws DAOException {
		ValidatorUtils.validate(running);
		
		LOG.info("JDBCRunningDAO --> delete : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DELETED + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEACHER_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, running.getProject().getCode());
			preparedStatement.setString(3, running.getTeacher().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCRunningDAO --> delete failed", "Running not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public Running select(long id) throws DAOException {
		LOG.info("JDBCRunningDAO --> select : id=" + id);

		Running running = null;

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
				running = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCRunningDAO --> select failed", "Running not readed");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return running;
	}

	public List<Running> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCRunningDAO --> select : parameters=" + URLQueryUtils.toString(parameters));

		List<Running> runnings = new ArrayList<>();

		Statement statement = null;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM ");
			builder.append(TABLE);
			builder.append(" WHERE ");
			builder.append(ConditionBuilder.parse(parameters, new RunningExpressionBuilder()));
			builder.append(" AND ");
			builder.append(COLUMN_DELETED + " = false");

			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				runnings.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCRunningDAO --> select failed", "Runnings not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return runnings;
	}

	@Override
	public List<Running> selectAll() throws DAOException {
		LOG.info("JDBCRunningDAO --> selectAll");

		List<Running> runnings = new ArrayList<>();

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
				runnings.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCRunningDAO --> selectAll failed", "Runnings not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return runnings;
	}
}

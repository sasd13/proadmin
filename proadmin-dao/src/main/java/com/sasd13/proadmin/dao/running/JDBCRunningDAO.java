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
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.dao.JDBCEntityDAO;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCEntityDAO<Running> implements IRunningDAO {

	private static final Logger LOG = Logger.getLogger(JDBCRunningDAO.class);

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setLong(2, running.getTeacher().getId());
		preparedStatement.setLong(3, running.getProject().getId());
	}

	@Override
	protected Running getResultSetValues(ResultSet resultSet) throws SQLException {
		Running running = new Running();
		running.setId(resultSet.getLong(COLUMN_ID));
		running.setYear(resultSet.getInt(COLUMN_YEAR));

		Teacher teacher = new Teacher();
		teacher.setId(resultSet.getLong(COLUMN_TEACHER_ID));
		running.setTeacher(teacher);

		Project project = new Project();
		project.setId(resultSet.getLong(COLUMN_PROJECT_ID));
		running.setProject(project);

		return running;
	}

	@Override
	public long insert(Running running) throws DAOException {
		LOG.info("JDBCRunningDAO --> insert : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_YEAR);
		builder.append(", " + COLUMN_TEACHER_ID);
		builder.append(", " + COLUMN_PROJECT_ID);
		builder.append(") VALUES (?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, running);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
				running.setId(id);
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
		LOG.info("JDBCRunningDAO --> update : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(", " + COLUMN_TEACHER_ID + " = ?");
		builder.append(", " + COLUMN_PROJECT_ID + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, running);
			preparedStatement.setLong(4, running.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCRunningDAO --> update failed", "Running not updated");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(Running running) throws DAOException {
		LOG.info("JDBCRunningDAO --> delete : projectCode=" + running.getProject().getCode() + ", teacherNumber=" + running.getTeacher().getNumber());

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
			preparedStatement.setLong(2, running.getId());

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
			doCatch(e, LOG, "JDBCRunningDAO --> select failed", "Running not selected");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return running;
	}

	public List<Running> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCRunningDAO --> select : parameters=" + URLQueryEncoder.toString(parameters));

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
			doCatch(e, LOG, "JDBCRunningDAO --> select failed", "Runnings not selected");
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
			doCatch(e, LOG, "JDBCRunningDAO --> selectAll failed", "Runnings not selected");
		} finally {
			doFinally(statement, LOG);
		}

		return runnings;
	}
}

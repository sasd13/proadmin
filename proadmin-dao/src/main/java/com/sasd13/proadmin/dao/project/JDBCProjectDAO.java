/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.project;

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
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.dao.validator.ValidatorUtils;

/**
 *
 * @author Samir
 */
public class JDBCProjectDAO extends JDBCEntityDAO<Project> implements IProjectDAO {

	private static final Logger LOG = Logger.getLogger(JDBCProjectDAO.class);

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Project project) throws SQLException {
		preparedStatement.setString(1, project.getCode());
		preparedStatement.setString(2, project.getTitle());
		preparedStatement.setString(3, project.getDescription());
	}

	@Override
	protected Project getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_CODE));
		project.setTitle(resultSet.getString(COLUMN_TITLE));
		project.setDescription(resultSet.getString(COLUMN_DESCRIPTION));

		return project;
	}

	@Override
	public long insert(Project project) throws DAOException {
		ValidatorUtils.validate(project);

		LOG.info("JDBCProjectDAO --> insert : code=" + project.getCode());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_TITLE);
		builder.append(", " + COLUMN_DESCRIPTION);
		builder.append(") VALUES (?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, project);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCProjectDAO --> insert failed", "Project not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(Project project) throws DAOException {
		ValidatorUtils.validate(project);

		LOG.info("JDBCProjectDAO --> update : code=" + project.getCode());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(", " + COLUMN_TITLE + " = ?");
		builder.append(", " + COLUMN_DESCRIPTION + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, project);
			preparedStatement.setString(4, project.getCode());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCProjectDAO --> update failed", "Project not updated");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(Project project) throws DAOException {
		ValidatorUtils.validate(project);

		LOG.info("JDBCProjectDAO --> delete : code=" + project.getCode());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DELETED + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, project.getCode());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCProjectDAO --> delete failed", "Project not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public Project select(long id) throws DAOException {
		LOG.info("JDBCProjectDAO --> select : id=" + id);

		Project project = null;

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
				project = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCProjectDAO --> select failed", "Project not readed");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return project;
	}

	public List<Project> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCProjectDAO --> select : parameters=" + URLQueryUtils.toString(parameters));

		List<Project> projects = new ArrayList<>();

		Statement statement = null;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM ");
			builder.append(TABLE);
			builder.append(" WHERE ");
			builder.append(ConditionBuilder.parse(parameters, new ProjectExpressionBuilder()));
			builder.append(" AND ");
			builder.append(COLUMN_DELETED + " = false");

			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				projects.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCProjectDAO --> select failed", "Projects not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return projects;
	}

	@Override
	public List<Project> selectAll() throws DAOException {
		LOG.info("JDBCProjectDAO --> selectAll");

		List<Project> projects = new ArrayList<>();

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
				projects.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCProjectDAO --> selectAll failed", "Projects not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return projects;
	}
}

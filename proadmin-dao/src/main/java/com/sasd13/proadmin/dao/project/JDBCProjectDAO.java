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
import java.sql.Timestamp;
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
import com.sasd13.proadmin.bean.project.Project;

/**
 *
 * @author Samir
 */
public class JDBCProjectDAO extends JDBCEntityDAO<Project> implements IProjectDAO {

	private static final Logger LOG = Logger.getLogger(JDBCProjectDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCProjectDAO() {
		expressionBuilder = new ProjectExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Project project) throws SQLException {
		preparedStatement.setString(1, project.getCode());
		preparedStatement.setString(2, String.valueOf(project.getDateCreation()));
		preparedStatement.setString(3, project.getTitle());
		preparedStatement.setString(4, project.getDescription());
	}

	@Override
	protected Project getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_CODE));
		project.setDateCreation(Timestamp.valueOf(resultSet.getString(COLUMN_DATECREATION)));
		project.setTitle(resultSet.getString(COLUMN_TITLE));
		project.setDescription(resultSet.getString(COLUMN_DESCRIPTION));

		return project;
	}

	@Override
	public long insert(Project project) throws DAOException {
		LOG.info("insert : code=" + project.getCode());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_DATECREATION);
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
			doCatchWithThrow(e, "Project not inserted", LOG);
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(Project project) throws DAOException {
		LOG.info("update : code=" + project.getCode());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(", " + COLUMN_DATECREATION + " = ?");
		builder.append(", " + COLUMN_TITLE + " = ?");
		builder.append(", " + COLUMN_DESCRIPTION + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, project);
			preparedStatement.setString(5, project.getCode());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(e, "Project not updated", LOG);
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(Project project) throws DAOException {
		LOG.info("delete : code=" + project.getCode());

		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, project.getCode());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(e, "Project not deleted", LOG);
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public Project select(long id) throws DAOException {
		LOG.info("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<Project> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("select : parameters=" + URLQueryUtils.toString(parameters));

		List<Project> projects = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");

		try {
			builder.append(ConditionBuilder.parse(parameters, expressionBuilder));
		} catch (ConditionException e) {
			doCatchWithThrow(e, "Paramaters parsing error", LOG);
		}

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				projects.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(e, "Projects not readed", LOG);
		} finally {
			doFinally(statement, LOG);
		}

		return projects;
	}

	@Override
	public List<Project> selectAll() throws DAOException {
		LOG.info("selectAll");

		List<Project> projects = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				projects.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(e, "Projects not readed", LOG);
		} finally {
			doFinally(statement, LOG);
		}

		return projects;
	}
}

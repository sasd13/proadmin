/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.JDBCUtils;
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
	public long insert(Project project) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(", " + COLUMN_DATECREATION);
		builder.append(", " + COLUMN_TITLE);
		builder.append(", " + COLUMN_DESCRIPTION);
		builder.append(") VALUES (?, ?, ?)");

		return JDBCUtils.insert(this, builder.toString(), project);
	}

	@Override
	public void update(Project project) throws DAOException {
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

		JDBCUtils.update(this, builder.toString(), project);
	}

	@Override
	public void delete(Project project) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), project);
	}

	@Override
	public Project select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public List<Project> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<Project> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public void editPreparedStatement(PreparedStatement preparedStatement, Project project) throws SQLException {
		preparedStatement.setString(1, project.getCode());
		preparedStatement.setString(2, String.valueOf(project.getDateCreation()));
		preparedStatement.setString(3, project.getTitle());
		preparedStatement.setString(4, project.getDescription());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, Project project) throws SQLException {
		super.editPreparedStatementForUpdate(preparedStatement, project);

		preparedStatement.setString(5, project.getCode());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Project project) throws SQLException {
		super.editPreparedStatementForDelete(preparedStatement, project);

		preparedStatement.setString(1, project.getCode());
	}

	@Override
	public Project getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_CODE));
		project.setDateCreation(Timestamp.valueOf(resultSet.getString(COLUMN_DATECREATION)));
		project.setTitle(resultSet.getString(COLUMN_TITLE));
		project.setDescription(resultSet.getString(COLUMN_DESCRIPTION));

		return project;
	}
}

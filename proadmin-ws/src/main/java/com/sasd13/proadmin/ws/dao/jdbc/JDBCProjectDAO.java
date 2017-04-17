/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;
import com.sasd13.proadmin.ws.dao.IProjectDAO;

/**
 *
 * @author Samir
 */
public class JDBCProjectDAO extends JDBCSession<Project> implements IProjectDAO {

	@Override
	public long create(Project project) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_DATECREATION);
		builder.append(", " + COLUMN_TITLE);
		builder.append(", " + COLUMN_DESCRIPTION);
		builder.append(") VALUES (?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), project);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(ProjectUpdateWrapper updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DATECREATION + " = ?");
		builder.append(", " + COLUMN_TITLE + " = ?");
		builder.append(", " + COLUMN_DESCRIPTION + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.update(this, builder.toString(), updateWrapper);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Project project) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), project);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Project> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Project> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Project project) throws SQLException {
		preparedStatement.setTimestamp(1, new Timestamp(project.getDateCreation().getTime()));
		preparedStatement.setString(2, project.getTitle());
		preparedStatement.setString(3, project.getDescription());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Project> updateWrapper) throws SQLException {
		Project project = updateWrapper.getWrapped();

		preparedStatement.setTimestamp(1, new Timestamp(project.getDateCreation().getTime()));
		preparedStatement.setString(2, project.getTitle());
		preparedStatement.setString(3, project.getDescription());
		preparedStatement.setString(4, ((ProjectUpdateWrapper) updateWrapper).getCode());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Project project) throws SQLException {
		preparedStatement.setString(1, project.getCode());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_CODE + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public Project getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();

		project.setCode(resultSet.getString(COLUMN_CODE));
		project.setDateCreation(new Date(resultSet.getTimestamp(COLUMN_DATECREATION).getTime()));
		project.setTitle(resultSet.getString(COLUMN_TITLE));
		project.setDescription(resultSet.getString(COLUMN_DESCRIPTION));

		return project;
	}
}

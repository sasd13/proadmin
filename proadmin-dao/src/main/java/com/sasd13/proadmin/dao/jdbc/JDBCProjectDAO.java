/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.jdbc;

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
import com.sasd13.proadmin.dao.IProjectDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.project.ProjectUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCProjectDAO extends JDBCSession<Project> implements IProjectDAO {

	@Override
	public long insert(Project project) {
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
	public void update(IUpdateWrapper<Project> updateWrapper) {
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

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Project project) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), project);
	}

	@Override
	public Project select(long id) {
		return null;
	}

	@Override
	public List<Project> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Project> selectAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Project project) {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Project project) throws SQLException {
		preparedStatement.setString(1, project.getCode());
		preparedStatement.setTimestamp(2, new Timestamp(project.getDateCreation().getTime()));
		preparedStatement.setString(3, project.getTitle());
		preparedStatement.setString(4, project.getDescription());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Project> updateWrapper) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, updateWrapper.getWrapped());

		preparedStatement.setString(5, ((ProjectUpdateWrapper) updateWrapper).getCode());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Project project) throws SQLException {
		preparedStatement.setString(1, project.getCode());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_CODE + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
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

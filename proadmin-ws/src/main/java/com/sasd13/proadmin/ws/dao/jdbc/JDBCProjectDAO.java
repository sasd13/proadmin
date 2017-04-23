/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.dao.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.joda.time.DateTime;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.bean.update.ProjectUpdate;
import com.sasd13.proadmin.ws.dao.IProjectDAO;

/**
 *
 * @author Samir
 */
public class JDBCProjectDAO extends JDBCSession<IProject> implements IProjectDAO {

	@Override
	public long create(IProject iProject) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_DATECREATION);
		builder.append(", " + COLUMN_TITLE);
		builder.append(", " + COLUMN_DESCRIPTION);
		builder.append(") VALUES (?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), iProject);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(ProjectUpdate updateWrapper) {
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
	public void delete(IProject iProject) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), iProject);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IProject> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IProject> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, IProject iProject) throws SQLException {
		preparedStatement.setTimestamp(1, new Timestamp(iProject.getDateCreation().getTime()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		preparedStatement.setString(2, iProject.getTitle());
		preparedStatement.setString(3, iProject.getDescription());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<IProject> updateWrapper) throws SQLException {
		IProject iProject = updateWrapper.getWrapped();

		preparedStatement.setTimestamp(1, new Timestamp(iProject.getDateCreation().getTime()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		preparedStatement.setString(2, iProject.getTitle());
		preparedStatement.setString(3, iProject.getDescription());
		preparedStatement.setString(4, ((ProjectUpdate) updateWrapper).getCode());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, IProject iProject) throws SQLException {
		preparedStatement.setString(1, iProject.getCode());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_CODE + " = ?";
		} else if (EnumParameter.START_DATE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_DATECREATION + " >= ?";
		} else if (EnumParameter.END_DATE.getName().equalsIgnoreCase(key)) {
			return IProjectDAO.COLUMN_DATECREATION + " <= ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.CODE.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.START_DATE.getName().equalsIgnoreCase(key)) {
			preparedStatement.setTimestamp(index, new Timestamp(new DateTime(value).getMillis()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		} else if (EnumParameter.END_DATE.getName().equalsIgnoreCase(key)) {
			preparedStatement.setTimestamp(index, new Timestamp(new DateTime(value).getMillis()), Calendar.getInstance(TimeZone.getTimeZone("GMT")));
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public IProject getResultSetValues(ResultSet resultSet) throws SQLException {
		IProject iProject = new IProject();

		iProject.setCode(resultSet.getString(COLUMN_CODE));
		iProject.setDateCreation(new Date(resultSet.getTimestamp(COLUMN_DATECREATION).getTime()));
		iProject.setTitle(resultSet.getString(COLUMN_TITLE));
		iProject.setDescription(resultSet.getString(COLUMN_DESCRIPTION));

		return iProject;
	}
}

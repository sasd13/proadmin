/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.running;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.dao.jdbc.ConditionException;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.builder.running.RunningBaseBuilder;
import com.sasd13.proadmin.util.wrapper.update.running.IRunningUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCSession<Running> implements IRunningDAO {

	@Override
	public long insert(Running running) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_YEAR);
		builder.append(", " + COLUMN_PROJECT_CODE);
		builder.append(", " + COLUMN_TEACHER_CODE);
		builder.append(") VALUES (?, ?, ?)");

		return JDBCUtils.insert(this, builder.toString(), running);
	}

	@Override
	public void update(IUpdateWrapper<Running> updateWrapper) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(", " + COLUMN_PROJECT_CODE + " = ?");
		builder.append(", " + COLUMN_TEACHER_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEACHER_CODE + " = ?");

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Running running) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEACHER_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), running);
	}

	@Override
	public Running select(long id) throws DAOException {
		return null;
	}

	@Override
	public List<Running> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Running> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Running running) throws DAOException {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setString(2, running.getProject().getCode());
		preparedStatement.setString(3, running.getTeacher().getNumber());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Running> updateWrapper) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, updateWrapper.getWrapped());

		preparedStatement.setInt(4, ((IRunningUpdateWrapper) updateWrapper).getYear());
		preparedStatement.setString(5, ((IRunningUpdateWrapper) updateWrapper).getProjectCode());
		preparedStatement.setString(6, ((IRunningUpdateWrapper) updateWrapper).getTeacherNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setString(2, running.getProject().getCode());
		preparedStatement.setString(3, running.getTeacher().getNumber());
	}

	@Override
	public String buildCondition(String key) throws ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_YEAR + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_PROJECT_CODE + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_TEACHER_CODE + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			try {
				preparedStatement.setInt(index, Integer.parseInt(value));
			} catch (NumberFormatException e) {
				throw new ConditionException("Parameter " + key + " parsing error");
			}
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public Running getResultSetValues(ResultSet resultSet) throws SQLException {
		Running running = new RunningBaseBuilder(resultSet.getInt(COLUMN_YEAR), resultSet.getString(COLUMN_PROJECT_CODE), resultSet.getString(COLUMN_TEACHER_CODE)).build();

		return running;
	}
}

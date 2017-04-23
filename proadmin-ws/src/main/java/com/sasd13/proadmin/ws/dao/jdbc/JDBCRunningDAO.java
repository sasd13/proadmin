/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.ITeacher;
import com.sasd13.proadmin.bean.project.IProject;
import com.sasd13.proadmin.bean.running.IRunning;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws.dao.IRunningDAO;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCSession<IRunning> implements IRunningDAO {

	@Override
	public long create(IRunning iRunning) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_YEAR);
		builder.append(", " + COLUMN_PROJECT);
		builder.append(", " + COLUMN_TEACHER);
		builder.append(") VALUES (?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), iRunning);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(RunningUpdate updateWrapper) {
		// StringBuilder builder = new StringBuilder();
		// builder.append("UPDATE ");
		// builder.append(TABLE);
		// builder.append(" SET ");
		// builder.append(" WHERE ");
		// builder.append(COLUMN_YEAR + " = ?");
		// builder.append(" AND " + COLUMN_PROJECT + " = ?");
		// builder.append(" AND " + COLUMN_TEACHER + " = ?");
		//
		// JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(IRunning iRunning) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT + " = ?");
		builder.append(" AND " + COLUMN_TEACHER + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), iRunning);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IRunning> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<IRunning> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, IRunning iRunning) throws SQLException {
		preparedStatement.setInt(1, iRunning.getYear());
		preparedStatement.setString(2, iRunning.getProject().getCode());
		preparedStatement.setString(3, iRunning.getTeacher().getIntermediary());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<IRunning> updateWrapper) throws SQLException {
		// Running running = updateWrapper.getWrapped();
		//
		// preparedStatement.setInt(4, ((RunningUpdateWrapper) updateWrapper).getYear());
		// preparedStatement.setString(5, ((RunningUpdateWrapper) updateWrapper).getProjectCode());
		// preparedStatement.setString(6, ((RunningUpdateWrapper) updateWrapper).getTeacherNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, IRunning iRunning) throws SQLException {
		preparedStatement.setInt(1, iRunning.getYear());
		preparedStatement.setString(2, iRunning.getProject().getCode());
		preparedStatement.setString(3, iRunning.getTeacher().getIntermediary());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_YEAR + " = ?";
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_PROJECT + " = ?";
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			return IRunningDAO.COLUMN_TEACHER + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.YEAR.getName().equalsIgnoreCase(key)) {
			preparedStatement.setInt(index, Integer.parseInt(value));
		} else if (EnumParameter.PROJECT.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.TEACHER.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public IRunning getResultSetValues(ResultSet resultSet) throws SQLException {
		IRunning iRunning = new IRunning();

		iRunning.setYear(resultSet.getInt(COLUMN_YEAR));

		IProject iProject = new IProject();
		iProject.setCode(resultSet.getString(COLUMN_PROJECT));

		iRunning.setProject(iProject);

		ITeacher iTeacher = new ITeacher();
		iTeacher.setIntermediary(resultSet.getString(COLUMN_TEACHER));

		iRunning.setTeacher(iTeacher);

		return iRunning;
	}
}

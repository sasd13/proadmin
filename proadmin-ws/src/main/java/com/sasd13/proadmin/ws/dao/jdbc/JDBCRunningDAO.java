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
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.running.RunningUpdateWrapper;
import com.sasd13.proadmin.ws.dao.IRunningDAO;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCSession<Running> implements IRunningDAO {

	@Override
	public long create(Running running) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_YEAR);
		builder.append(", " + COLUMN_PROJECT);
		builder.append(", " + COLUMN_TEACHER);
		builder.append(") VALUES (?, ?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), running);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(RunningUpdateWrapper updateWrapper) {
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
	public void delete(Running running) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(" AND " + COLUMN_PROJECT + " = ?");
		builder.append(" AND " + COLUMN_TEACHER + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), running);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Running> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Running> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setString(2, running.getProject().getCode());
		preparedStatement.setString(3, running.getTeacher().getIntermediary());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Running> updateWrapper) throws SQLException {
		// Running running = updateWrapper.getWrapped();
		//
		// preparedStatement.setInt(4, ((RunningUpdateWrapper) updateWrapper).getYear());
		// preparedStatement.setString(5, ((RunningUpdateWrapper) updateWrapper).getProjectCode());
		// preparedStatement.setString(6, ((RunningUpdateWrapper) updateWrapper).getTeacherNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setString(2, running.getProject().getCode());
		preparedStatement.setString(3, running.getTeacher().getIntermediary());
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
	public Running getResultSetValues(ResultSet resultSet) throws SQLException {
		Running running = new Running();

		running.setYear(resultSet.getInt(COLUMN_YEAR));

		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_PROJECT));

		running.setProject(project);

		Teacher teacher = new Teacher();
		teacher.setIntermediary(resultSet.getString(COLUMN_TEACHER));

		running.setTeacher(teacher);

		return running;
	}
}

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
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.builder.running.RunningBaseBuilder;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCSession<Running> implements IRunningDAO {

	private IExpressionBuilder expressionBuilder;

	public JDBCRunningDAO() {
		expressionBuilder = new RunningExpressionBuilder();
	}

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
	public void update(Running running) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_YEAR + " = ?");
		builder.append(", " + COLUMN_PROJECT_CODE + " = ?");
		builder.append(", " + COLUMN_TEACHER_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEACHER_CODE + " = ?");

		JDBCUtils.update(this, builder.toString(), running);
	}

	@Override
	public void delete(Running running) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEACHER_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), running);
	}

	@Override
	public Running select(long id) throws DAOException {
		return null;
	}

	@Override
	public List<Running> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
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
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, Running running) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, running);

		preparedStatement.setString(4, running.getProject().getCode());
		preparedStatement.setString(5, running.getTeacher().getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Running running) throws SQLException {
		preparedStatement.setString(1, running.getProject().getCode());
		preparedStatement.setString(2, running.getTeacher().getNumber());
	}

	@Override
	public Running getResultSetValues(ResultSet resultSet) throws SQLException {
		Running running = new RunningBaseBuilder(
				resultSet.getInt(COLUMN_YEAR), 
				resultSet.getString(COLUMN_PROJECT_CODE), 
				resultSet.getString(COLUMN_TEACHER_CODE)).build();

		return running;
	}
}

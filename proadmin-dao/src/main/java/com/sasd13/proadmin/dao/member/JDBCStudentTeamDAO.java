/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.util.builder.member.StudentTeamBaseBuilder;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCSession<StudentTeam> implements IStudentTeamDAO {

	private IExpressionBuilder expressionBuilder;

	public JDBCStudentTeamDAO() {
		expressionBuilder = new StudentTeamExpressionBuilder();
	}

	@Override
	public long insert(StudentTeam studentTeam) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_STUDENT_CODE);
		builder.append(", " + COLUMN_TEAM_CODE);
		builder.append(") VALUES (?, ?)");

		return JDBCUtils.insert(this, builder.toString(), studentTeam);
	}

	@Override
	public void update(IUpdateWrapper<StudentTeam> updateWrapper) throws DAOException {
		// Do nothing
	}

	@Override
	public void delete(StudentTeam studentTeam) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_STUDENT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEAM_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), studentTeam);
	}

	@Override
	public StudentTeam select(long id) throws DAOException {
		return null;
	}

	@Override
	public List<StudentTeam> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<StudentTeam> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(StudentTeam studentTeam) throws DAOException {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, StudentTeam studentTeam) throws SQLException {
		preparedStatement.setString(1, studentTeam.getStudent().getNumber());
		preparedStatement.setString(2, studentTeam.getTeam().getNumber());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<StudentTeam> updateWrapper) throws SQLException {
		// Do nothing
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, StudentTeam studentTeam) throws SQLException {
		preparedStatement.setString(1, studentTeam.getStudent().getNumber());
		preparedStatement.setString(2, studentTeam.getTeam().getNumber());
	}

	@Override
	public StudentTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		StudentTeam studentTeam = new StudentTeamBaseBuilder(resultSet.getString(COLUMN_STUDENT_CODE), resultSet.getString(COLUMN_TEAM_CODE)).build();

		return studentTeam;
	}
}

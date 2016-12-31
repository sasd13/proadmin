/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.dao.IStudentTeamDAO;
import com.sasd13.proadmin.util.EnumParameter;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCSession<StudentTeam> implements IStudentTeamDAO {

	@Override
	public long insert(StudentTeam studentTeam) {
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
	public void update(IUpdateWrapper<StudentTeam> updateWrapper) {
		// Do nothing
	}

	@Override
	public void delete(StudentTeam studentTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_STUDENT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEAM_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), studentTeam);
	}

	@Override
	public StudentTeam select(long id) {
		return null;
	}

	@Override
	public List<StudentTeam> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<StudentTeam> selectAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(StudentTeam studentTeam) {
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
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_STUDENT_CODE;
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_TEAM_CODE;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public StudentTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		StudentTeam studentTeam = new StudentTeam(resultSet.getString(COLUMN_STUDENT_CODE), resultSet.getString(COLUMN_TEAM_CODE));

		return studentTeam;
	}
}

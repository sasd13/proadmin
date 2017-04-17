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
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.dao.IStudentTeamDAO;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCSession<StudentTeam> implements IStudentTeamDAO {

	@Override
	public long create(StudentTeam studentTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_STUDENT);
		builder.append(", " + COLUMN_TEAM);
		builder.append(") VALUES (?, ?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), studentTeam);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(StudentTeam studentTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_STUDENT + " = ?");
		builder.append(" AND " + COLUMN_TEAM + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), studentTeam);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<StudentTeam> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, StudentTeam studentTeam) throws SQLException {
		preparedStatement.setString(1, studentTeam.getStudent().getIntermediary());
		preparedStatement.setString(2, studentTeam.getTeam().getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, StudentTeam studentTeam) throws SQLException {
		preparedStatement.setString(1, studentTeam.getStudent().getIntermediary());
		preparedStatement.setString(2, studentTeam.getTeam().getNumber());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.STUDENT.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_STUDENT + " = ?";
		} else if (EnumParameter.TEAM.getName().equalsIgnoreCase(key)) {
			return IStudentTeamDAO.COLUMN_TEAM + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
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
		StudentTeam studentTeam = new StudentTeam();

		Student student = new Student();
		student.setIntermediary(resultSet.getString(COLUMN_STUDENT));

		studentTeam.setStudent(student);

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_TEAM));

		studentTeam.setTeam(team);

		return studentTeam;
	}
}

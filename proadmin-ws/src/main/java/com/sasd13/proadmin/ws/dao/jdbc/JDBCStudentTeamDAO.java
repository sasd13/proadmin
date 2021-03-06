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
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.Student;
import com.sasd13.proadmin.ws.bean.StudentTeam;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.dao.IStudentTeamDAO;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCSession<StudentTeam> implements IStudentTeamDAO {

	@Override
	public void create(List<StudentTeam> studentTeams) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_STUDENT);
		builder.append(", " + COLUMN_TEAM);
		builder.append(") VALUES (?, ?)");

		try {
			for (StudentTeam studentTeam : studentTeams) {
				JDBCUtils.insert(this, new String(builder.toString()), studentTeam);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(List<StudentTeam> studentTeams) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_STUDENT + " = ?");
		builder.append(" AND " + COLUMN_TEAM + " = ?");

		try {
			for (StudentTeam studentTeam : studentTeams) {
				JDBCUtils.delete(this, new String(builder.toString()), studentTeam);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<StudentTeam> read(Map<String, String[]> criterias) {
		try {
			return JDBCUtils.select(this, TABLE, criterias);
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
	public String getCondition(String key, int index) {
		if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			return COLUMN_STUDENT + " = ?";
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			return COLUMN_TEAM + " = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumCriteria.STUDENT.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.TEAM.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
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

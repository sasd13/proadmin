/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.condition.ConditionBuilder;
import com.sasd13.javaex.net.http.URLQueryEncoder;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.JDBCEntityDAO;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCEntityDAO<StudentTeam> implements IStudentTeamDAO {

	private static final Logger LOG = Logger.getLogger(JDBCStudentTeamDAO.class);

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, StudentTeam studentTeam) throws SQLException {
		preparedStatement.setLong(1, studentTeam.getStudent().getId());
		preparedStatement.setLong(2, studentTeam.getTeam().getId());
	}

	@Override
	protected StudentTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		student.setId(resultSet.getLong(COLUMN_STUDENT_ID));

		Team team = new Team();
		team.setId(resultSet.getLong(COLUMN_TEAM_ID));

		StudentTeam studentTeam = new StudentTeam(student, team);
		studentTeam.setId(resultSet.getLong(COLUMN_ID));

		return studentTeam;
	}

	@Override
	public long insert(StudentTeam studentTeam) throws DAOException {
		LOG.info("JDBCStudentTeamDAO --> insert : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getCode());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_STUDENT_ID);
		builder.append(", " + COLUMN_TEAM_ID);
		builder.append(") VALUES (?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, studentTeam);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
				studentTeam.setId(id);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCStudentTeamDAO --> insert failed", "StudentTeam not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(StudentTeam studentTeam) throws DAOException {
		LOG.info("JDBCStudentTeamDAO --> update unavailable");
	}

	@Override
	public void delete(StudentTeam studentTeam) throws DAOException {
		LOG.info("JDBCStudentTeamDAO --> delete : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getCode());

		StringBuilder builder = new StringBuilder();
		builder.append("DELTE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, studentTeam.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCStudentTeamDAO --> delete failed", "StudentTeam not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public StudentTeam select(long id) throws DAOException {
		LOG.info("JDBCStudentTeamDAO --> select : id=" + id);

		StudentTeam studentTeam = null;

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setLong(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				studentTeam = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCStudentTeamDAO --> select failed", "StudentTeam not selected");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return studentTeam;
	}

	public List<StudentTeam> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCStudentTeamDAO --> select : parameters=" + URLQueryEncoder.toString(parameters));

		List<StudentTeam> studentTeams = new ArrayList<>();

		Statement statement = null;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM ");
			builder.append(TABLE);
			builder.append(" WHERE ");
			builder.append(ConditionBuilder.parse(parameters, new StudentTeamExpressionBuilder()));

			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				studentTeams.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCStudentTeamDAO --> select failed", "StudentTeams not selected");
		} finally {
			doFinally(statement, LOG);
		}

		return studentTeams;
	}

	@Override
	public List<StudentTeam> selectAll() throws DAOException {
		LOG.info("JDBCStudentTeamDAO --> selectAll");

		List<StudentTeam> studentTeams = new ArrayList<StudentTeam>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				studentTeams.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCStudentTeamDAO --> selectAll failed", "StudentTeams not selected");
		} finally {
			doFinally(statement, LOG);
		}

		return studentTeams;
	}
}

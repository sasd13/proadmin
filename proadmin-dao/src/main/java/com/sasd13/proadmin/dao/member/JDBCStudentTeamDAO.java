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

import com.sasd13.javaex.dao.ConditionBuilder;
import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.net.URLQueryUtils;
import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCEntityDAO<StudentTeam> implements IStudentTeamDAO {

	private static final Logger LOG = Logger.getLogger(JDBCStudentTeamDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCStudentTeamDAO() {
		expressionBuilder = new StudentTeamExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, StudentTeam studentTeam) throws SQLException {
		preparedStatement.setString(1, studentTeam.getStudent().getNumber());
		preparedStatement.setString(2, studentTeam.getTeam().getNumber());
	}

	@Override
	protected StudentTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		student.setNumber(resultSet.getString(COLUMN_STUDENT_CODE));

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_TEAM_CODE));

		StudentTeam studentTeam = new StudentTeam(student, team);

		return studentTeam;
	}

	@Override
	public long insert(StudentTeam studentTeam) throws DAOException {
		LOG.info("insert : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_STUDENT_CODE);
		builder.append(", " + COLUMN_TEAM_CODE);
		builder.append(") VALUES (?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, studentTeam);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "insert failed", "StudentTeam not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(StudentTeam studentTeam) throws DAOException {
		LOG.info("update unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public void delete(StudentTeam studentTeam) throws DAOException {
		LOG.info("delete : studentNumber=" + studentTeam.getStudent().getNumber() + ", teamCode=" + studentTeam.getTeam().getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_STUDENT_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEAM_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, studentTeam.getStudent().getNumber());
			preparedStatement.setString(2, studentTeam.getTeam().getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "delete failed", "StudentTeam not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public StudentTeam select(long id) throws DAOException {
		LOG.info("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<StudentTeam> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("select : parameters=" + URLQueryUtils.toString(parameters));

		List<StudentTeam> studentTeams = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");

		try {
			builder.append(ConditionBuilder.parse(parameters, expressionBuilder));
		} catch (ConditionException e) {
			doCatchWithThrow(LOG, "select failed", e.getMessage());
		}

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				studentTeams.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "select failed", "StudentTeams not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return studentTeams;
	}

	@Override
	public List<StudentTeam> selectAll() throws DAOException {
		LOG.info("selectAll");

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
			doCatchWithThrow(LOG, "selectAll failed", "StudentTeams not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return studentTeams;
	}
}

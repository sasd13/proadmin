/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.running;

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
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;

/**
 *
 * @author Samir
 */
public class JDBCRunningTeamDAO extends JDBCEntityDAO<RunningTeam> implements IRunningTeamDAO {

	private static final Logger LOG = Logger.getLogger(JDBCRunningTeamDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCRunningTeamDAO() {
		expressionBuilder = new RunningTeamExpressionBuilder();
	}

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		preparedStatement.setString(1, runningTeam.getRunning().getProject().getCode());
		preparedStatement.setString(2, runningTeam.getRunning().getTeacher().getNumber());
		preparedStatement.setString(3, runningTeam.getTeam().getNumber());
		preparedStatement.setString(4, runningTeam.getAcademicLevel().getCode());
	}

	@Override
	protected RunningTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		Project project = new Project();
		project.setCode(resultSet.getString(COLUMN_RUNNING_PROJECT_CODE));

		Teacher teacher = new Teacher();
		teacher.setNumber(resultSet.getString(COLUMN_RUNNING_TEACHER_CODE));

		Running running = new Running(project);
		running.setTeacher(teacher);

		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_TEAM_CODE));

		RunningTeam runningTeam = new RunningTeam(running, team);
		runningTeam.setAcademicLevel(new AcademicLevel(resultSet.getString(COLUMN_ACADEMICLEVEL_CODE)));

		return runningTeam;
	}

	@Override
	public long insert(RunningTeam runningTeam) throws DAOException {
		LOG.info("insert : projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_RUNNING_PROJECT_CODE);
		builder.append(", " + COLUMN_RUNNING_TEACHER_CODE);
		builder.append(", " + COLUMN_TEAM_CODE);
		builder.append(", " + COLUMN_ACADEMICLEVEL_CODE);
		builder.append(") VALUES (?, ?, ?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, runningTeam);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "insert failed", "RunningTeam not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(RunningTeam runningTeam) throws DAOException {
		LOG.info("update unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public void delete(RunningTeam runningTeam) throws DAOException {
		LOG.info("delete : projectCode=" + runningTeam.getRunning().getProject().getCode() + ", teacherNumber=" + runningTeam.getRunning().getTeacher().getNumber() + ", teamNumber=" + runningTeam.getTeam().getNumber() + ", academicLevel=" + runningTeam.getAcademicLevel().getCode());

		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_RUNNING_PROJECT_CODE + " = ?");
		builder.append(" AND " + COLUMN_RUNNING_TEACHER_CODE + " = ?");
		builder.append(" AND " + COLUMN_TEAM_CODE + " = ?");
		builder.append(" AND " + COLUMN_ACADEMICLEVEL_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, runningTeam.getRunning().getProject().getCode());
			preparedStatement.setString(2, runningTeam.getRunning().getTeacher().getNumber());
			preparedStatement.setString(3, runningTeam.getTeam().getNumber());
			preparedStatement.setString(4, runningTeam.getAcademicLevel().getCode());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "delete failed", "RunningTeam not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public RunningTeam select(long id) throws DAOException {
		LOG.info("select unavailable");
		throw new DAOException("Request unavailable");
	}

	public List<RunningTeam> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("select : parameters=" + URLQueryUtils.toString(parameters));

		List<RunningTeam> runningTeams = new ArrayList<>();

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
				runningTeams.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "select failed", "RunningTeams not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> selectAll() throws DAOException {
		LOG.info("selectAll");

		List<RunningTeam> runningTeams = new ArrayList<RunningTeam>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				runningTeams.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatchWithThrow(LOG, "selectAll failed", "RunningTeams not inserted");
		} finally {
			doFinally(statement, LOG);
		}

		return runningTeams;
	}
}

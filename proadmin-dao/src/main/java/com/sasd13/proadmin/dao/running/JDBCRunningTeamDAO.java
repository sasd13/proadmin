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

import com.sasd13.javaex.dao.condition.ConditionBuilder;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.JDBCEntityDAO;

/**
 *
 * @author Samir
 */
public class JDBCRunningTeamDAO extends JDBCEntityDAO<RunningTeam> implements IRunningTeamDAO {

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, RunningTeam runningTeam) throws SQLException {
		preparedStatement.setLong(1, runningTeam.getRunning().getId());
		preparedStatement.setLong(2, runningTeam.getTeam().getId());
	}

	@Override
	protected RunningTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		Running running = new Running();
		running.setId(resultSet.getLong(COLUMN_RUNNING_ID));

		Team team = new Team();
		team.setId(resultSet.getLong(COLUMN_TEAM_ID));

		RunningTeam runningTeam = new RunningTeam(running, team);
		runningTeam.setId(resultSet.getLong(COLUMN_ID));

		return runningTeam;
	}

	@Override
	public long insert(RunningTeam runningTeam) {
		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_RUNNING_ID);
		builder.append(", " + COLUMN_TEAM_ID);
		builder.append(") VALUES (?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, runningTeam);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
				runningTeam.setId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return id;
	}

	@Override
	public void update(RunningTeam runningTeam) {
		// Do nothing
	}

	@Override
	public void delete(RunningTeam runningTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELTE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, runningTeam.getId());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public RunningTeam select(long id) {
		RunningTeam runningTeam = null;

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
				runningTeam = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return runningTeam;
	}

	public List<RunningTeam> select(Map<String, String[]> parameters) {
		List<RunningTeam> runningTeams = new ArrayList<>();

		Statement statement = null;

		try {
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT * FROM ");
			builder.append(TABLE);
			builder.append(" WHERE ");
			builder.append(ConditionBuilder.parse(parameters, RunningTeamConditionExpression.class));

			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				runningTeams.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return runningTeams;
	}

	@Override
	public List<RunningTeam> selectAll() {
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
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return runningTeams;
	}
}

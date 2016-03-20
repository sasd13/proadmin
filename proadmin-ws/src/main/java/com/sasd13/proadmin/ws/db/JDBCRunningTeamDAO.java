/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.bean.running.RunningTeam;
import com.sasd13.proadmin.dao.RunningTeamDAO;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

/**
 *
 * @author Samir
 */
public class JDBCRunningTeamDAO extends JDBCEntityDAO<RunningTeam> implements RunningTeamDAO {
	
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
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_RUNNING_ID + ", " 
					+ COLUMN_TEAM_ID 
				+ ") VALUES (?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, runningTeam);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
			}
			
			runningTeam.setId(id);
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
		//Do nothing
	}
	
	@Override
	public void delete(long id) {
		String query = "DELTE FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, id);
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
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
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
			String query = "SELECT * FROM " + TABLE
					+ " WHERE " + WhereClauseParser.parse(RunningTeamDAO.class, parameters) + ";";
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(runningTeams, resultSet);
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
	
	private void fillListWithResultSet(List<RunningTeam> runningTeams, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			runningTeams.add(getResultSetValues(resultSet));
		}
	}
	
	@Override
	public List<RunningTeam> selectAll() {
		List<RunningTeam> runningTeams = new ArrayList<RunningTeam>();
		
		String query = "SELECT * FROM " + TABLE;
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(runningTeams, resultSet);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.TeamDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCTeamDAO extends JDBCTableDAO<Team> implements TeamDAO {
	
	@Override
	protected PreparedStatement getPreparedStatement(String query, Team team) throws SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(query, team);
		
		preparedStatement.setString(1, team.getCode());
		preparedStatement.setLong(2, team.getRunning().getId());
		
		return preparedStatement;
	}
	
	@Override
	protected Team getResultSetValues(ResultSet resultSet) throws SQLException {
		Team team = new Team();
		
		team.setId(resultSet.getLong(TEAM_ID));
		team.setCode(resultSet.getString(TEAM_CODE));
		
		Running running = new Running();
		running.setId(resultSet.getLong(RUNNINGS_RUNNING_ID));
		team.setRunning(running);
		
		return team;
	}
	
	@Override
	public long insert(Team team) {
		long id = 0;
		
		String query = "INSERT INTO " + TEAM_TABLE_NAME 
				+ "(" 
					+ TEAM_CODE + ", " 
					+ RUNNINGS_RUNNING_ID 
				+ ") VALUES (?, ?)";
		
		try {			
			id = executeInsert(query, team);
			team.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(Team team) {
		String query = "UPDATE " + TEAM_TABLE_NAME 
				+ " SET " 
					+ TEAM_CODE + " = ?, " 
					+ RUNNINGS_RUNNING_ID + " = ?, " 
				+ " WHERE " 
					+ TEAM_ID + " = ?";
		
		try {			
			PreparedStatement preparedStatement = getPreparedStatement(query, team);
			preparedStatement.setLong(3, team.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(long id) {
		String query = "UPDATE " + TEAM_TABLE_NAME
				+ " SET " 
					+ DELETED + " = ?" 
				+ " WHERE " 
					+ TEAM_ID + " = ?";
		
		try {			
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Team select(long id) {
		Team team = null;
		
		String query = "SELECT * FROM " + TEAM_TABLE_NAME 
				+ " WHERE " 
					+ TEAM_ID + " = ?";
		
		try {			
			team = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return team;
	}
	
	@Override
	public List<Team> selectAll() {
		List<Team> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TEAM_TABLE_NAME;
		
		try {			
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public Team selectByCode(String code) {
		Team team = null;
		
		String query = "SELECT * FROM " + TEAM_TABLE_NAME 
				+ " WHERE " 
					+ TEAM_CODE + " = ?";
		
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, code);
			
			team = executeSelectSingleResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return team;
	}
	
	@Override
	public List<Team> selectByRunning(long runningId) {
		List<Team> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TEAM_TABLE_NAME 
				+ " WHERE " 
					+ RUNNINGS_RUNNING_ID + " = ?";
		
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, runningId);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

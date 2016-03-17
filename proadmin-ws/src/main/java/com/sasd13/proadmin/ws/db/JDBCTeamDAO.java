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
import com.sasd13.proadmin.dao.TeamDAO;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

/**
 *
 * @author Samir
 */
public class JDBCTeamDAO extends JDBCEntityDAO<Team> implements TeamDAO {
	
	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Team team) throws SQLException {
		preparedStatement.setString(1, team.getCode());
	}
	
	@Override
	protected Team getResultSetValues(ResultSet resultSet) throws SQLException {
		Team team = new Team();
		team.setId(resultSet.getLong(COLUMN_ID));
		team.setCode(resultSet.getString(COLUMN_CODE));
		
		return team;
	}
	
	@Override
	public long insert(Team team) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_CODE
				+ ") VALUES (?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, team);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
			}
			
			team.setId(id);
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
	public void update(Team team) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_CODE + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, team);
			
			preparedStatement.setLong(2, team.getId());
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
	public void delete(long id) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
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
	public Team select(long id) {
		Team team = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (!resultSet.getBoolean(COLUMN_DELETED)) {
					team = getResultSetValues(resultSet);
				}
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
		
		return team;
	}
	
	public List<Team> select(Map<String, String[]> parameters) {
		List<Team> list = new ArrayList<>();
		
		Statement statement = null;
		
		try {
			String query = "SELECT * FROM " + TABLE
					+ " WHERE " + WhereClauseParser.parse(TeamDAO.class, parameters) + ";";
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(list, resultSet);
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
		
		return list;
	}
	
	private void fillListWithResultSet(List<Team> list, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			if (!resultSet.getBoolean(COLUMN_DELETED)) {
				list.add(getResultSetValues(resultSet));
			}
		}
	}
	
	@Override
	public List<Team> selectAll() {
		List<Team> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE;
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(list, resultSet);
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
		
		return list;
	}
}

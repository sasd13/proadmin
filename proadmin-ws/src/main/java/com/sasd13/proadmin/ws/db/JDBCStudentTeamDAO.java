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

import com.sasd13.proadmin.bean.member.Student;
import com.sasd13.proadmin.bean.member.StudentTeam;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.StudentTeamDAO;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCEntityDAO<StudentTeam> implements StudentTeamDAO {
	
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
	public long insert(StudentTeam studentTeam) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_STUDENT_ID + ", " 
					+ COLUMN_TEAM_ID 
				+ ") VALUES (?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, studentTeam);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
			}
			
			studentTeam.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	@Override
	public void update(StudentTeam studentTeam) {
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
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public StudentTeam select(long id) {
		StudentTeam studentTeam = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				studentTeam = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return studentTeam;
	}
	
	public List<StudentTeam> select(Map<String, String[]> parameters) {
		List<StudentTeam> studentTeams = new ArrayList<>();
		
		Statement statement = null;
		
		try {
			String query = "SELECT * FROM " + TABLE
					+ " WHERE " + WhereClauseParser.parse(StudentTeamDAO.class, parameters) + ";";
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(studentTeams, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return studentTeams;
	}
	
	private void fillListWithResultSet(List<StudentTeam> studentTeams, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			studentTeams.add(getResultSetValues(resultSet));
		}
	}
	
	@Override
	public List<StudentTeam> selectAll() {
		List<StudentTeam> studentTeams = new ArrayList<StudentTeam>();
		
		String query = "SELECT * FROM " + TABLE;
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(studentTeams, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return studentTeams;
	}
}

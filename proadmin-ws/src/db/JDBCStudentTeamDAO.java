/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.bean.running.StudentTeam;
import com.sasd13.proadmin.core.bean.running.Team;
import com.sasd13.proadmin.core.db.StudentTeamDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCStudentTeamDAO extends JDBCEntityDAO<StudentTeam> implements StudentTeamDAO {
	
	@Override
	protected PreparedStatement getPreparedStatement(String query, StudentTeam studentTeam) throws SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(query, studentTeam);
		
		preparedStatement.setLong(1, studentTeam.getStudent().getId());
		preparedStatement.setLong(2, studentTeam.getTeam().getId());
		
		return preparedStatement;
	}
	
	@Override
	protected StudentTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		StudentTeam studentTeam = new StudentTeam();
		
		studentTeam.setId(resultSet.getLong(STUDENTTEAM_ID));
		
		Student student = new Student();
		student.setId(resultSet.getLong(STUDENTS_STUDENT_ID));
		studentTeam.setStudent(student);
		
		Team team = new Team();
		team.setId(resultSet.getLong(TEAMS_TEAM_ID));
		studentTeam.setTeam(team);
		
		return studentTeam;
	}
	
	@Override
	public long insert(StudentTeam studentTeam) {
		long id = 0;
		
		String query = "INSERT INTO " + STUDENTTEAM_TABLE_NAME 
				+ "(" 
					+ STUDENTS_STUDENT_ID + ", " 
					+ TEAMS_TEAM_ID 
				+ ") VALUES (?, ?)";
		
		try {
			id = executeInsert(query, studentTeam);
			studentTeam.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(StudentTeam arg0) {
		//Do nothing
	}
	
	@Override
	public void delete(long id) {
		String query = "DELTE FROM " + STUDENTTEAM_TABLE_NAME 
				+ " WHERE " 
					+ STUDENTTEAM_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public StudentTeam select(long id) {
		StudentTeam studentTeam = null;
		
		String query = "SELECT * FROM " + STUDENTTEAM_TABLE_NAME 
				+ " WHERE " 
					+ STUDENTTEAM_ID + " = ?";
		
		try {
			studentTeam = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return studentTeam;
	}
	
	@Override
	public List<StudentTeam> selectAll() {
		//Do nothing
		return null;
	}
	
	@Override
	public List<StudentTeam> selectByStudent(long studentId) {
		List<StudentTeam> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + STUDENTTEAM_TABLE_NAME 
				+ " WHERE " 
					+ STUDENTS_STUDENT_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, studentId);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<StudentTeam> selectByTeam(long teamId) {
		List<StudentTeam> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + STUDENTTEAM_TABLE_NAME 
				+ " WHERE " 
					+ TEAMS_TEAM_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, teamId);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

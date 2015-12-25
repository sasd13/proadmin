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
		
		preparedStatement.setLong(1, studentTeam.getTeam().getId());
		preparedStatement.setLong(2, studentTeam.getStudent().getId());
		
		return preparedStatement;
	}
	
	@Override
	protected StudentTeam getResultSetValues(ResultSet resultSet) throws SQLException {
		StudentTeam studentTeam = new StudentTeam();
		
		studentTeam.setId(resultSet.getLong(COLUMN_ID));
		
		Team team = new Team();
		team.setId(resultSet.getLong(COLUMN_TEAM_ID));
		studentTeam.setTeam(team);
		
		Student student = new Student();
		student.setId(resultSet.getLong(COLUMN_STUDENT_ID));
		studentTeam.setStudent(student);
		
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
		String query = "DELTE FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public StudentTeam select(long id) {
		StudentTeam studentTeam = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		try {
			studentTeam = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return studentTeam;
	}
	
	@Override
	public List<StudentTeam> selectAll() {
		List<StudentTeam> list = new ArrayList<StudentTeam>();
		
		String query = "SELECT * FROM " + TABLE;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

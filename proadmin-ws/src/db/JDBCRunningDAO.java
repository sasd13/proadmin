/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.bean.project.Project;
import com.sasd13.proadmin.core.bean.running.Running;
import com.sasd13.proadmin.core.db.RunningDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCRunningDAO extends JDBCTableDAO<Running> implements RunningDAO {
	
	@Override
	protected PreparedStatement getPreparedStatement(String query, Running running) throws SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(query, running);
		
		preparedStatement.setInt(1, running.getYear());
		preparedStatement.setLong(2, running.getTeacher().getId());
		preparedStatement.setLong(3, running.getProject().getId());
		
		return preparedStatement;
	}
	
	@Override
	protected Running getResultSetValues(ResultSet resultSet) throws SQLException {
		Running running = new Running();
		
		running.setId(resultSet.getLong(RUNNING_ID));
		running.setYear(resultSet.getInt(RUNNING_YEAR));
		
		Teacher teacher = new Teacher();
		teacher.setId(resultSet.getLong(TEACHERS_TEACHER_ID));
		running.setTeacher(teacher);
		
		Project project = new Project();
		project.setId(resultSet.getLong(PROJECTS_PROJECT_ID));
		running.setProject(project);
		
		return running;
	}
	
	@Override
	public long insert(Running running) {
		long id = 0;
		
		String query = "INSERT INTO " + RUNNING_TABLE_NAME 
				+ "(" 
					+ RUNNING_YEAR + ", " 
					+ TEACHERS_TEACHER_ID + ", " 
					+ PROJECTS_PROJECT_ID 
				+ ") VALUES (?, ?, ?)";
		
		try {			
			id = executeInsert(query, running);
			running.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(Running running) {
		String query = "UPDATE " + RUNNING_TABLE_NAME 
				+ " SET " 
					+ RUNNING_YEAR + " = ?, " 
					+ TEACHERS_TEACHER_ID + " = ?, " 
					+ PROJECTS_PROJECT_ID + " = ?, " 
				+ " WHERE " 
					+ RUNNING_ID + " = ?";
		
		try {			
			PreparedStatement preparedStatement = getPreparedStatement(query, running);
			preparedStatement.setLong(4, running.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(long id) {
		String query = "UPDATE " + RUNNING_TABLE_NAME
				+ " SET " 
					+ DELETED + " = ?" 
				+ " WHERE " 
					+ RUNNING_ID + " = ?";
		
		try {			
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Running select(long id) {
		Running running = null;
		
		String query = "SELECT * FROM " + RUNNING_TABLE_NAME 
				+ " WHERE " 
					+ RUNNING_ID + " = ?";
		
		try {			
			running = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return running;
	}
	
	@Override
	public List<Running> selectAll() {
		List<Running> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + RUNNING_TABLE_NAME;
		
		try {			
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<Running> selectByYear(int year) {
		List<Running> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + RUNNING_TABLE_NAME 
				+ " WHERE " 
					+ RUNNING_YEAR + " = ?";
		
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, year);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<Running> selectByTeacher(long teacherId) {
		List<Running> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + RUNNING_TABLE_NAME 
				+ " WHERE " 
					+ TEACHERS_TEACHER_ID + " = ?";
		
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, teacherId);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<Running> selectByProject(long projectId) {
		List<Running> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + RUNNING_TABLE_NAME 
				+ " WHERE " 
					+ PROJECTS_PROJECT_ID + " = ?";
		
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, projectId);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

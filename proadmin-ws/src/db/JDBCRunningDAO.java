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
public class JDBCRunningDAO extends JDBCEntityDAO<Running> implements RunningDAO {
	
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
		
		running.setId(resultSet.getLong(COLUMN_ID));
		running.setYear(resultSet.getInt(COLUMN_YEAR));
		
		Teacher teacher = new Teacher();
		teacher.setId(resultSet.getLong(COLUMN_TEACHER_ID));
		running.setTeacher(teacher);
		
		Project project = new Project();
		project.setId(resultSet.getLong(COLUMN_PROJECT_ID));
		running.setProject(project);
		
		return running;
	}
	
	@Override
	public long insert(Running running) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "("
					+ COLUMN_YEAR + ", "
					+ COLUMN_TEACHER_ID + ", "
					+ COLUMN_PROJECT_ID 
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
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_YEAR + " = ?, " 
					+ COLUMN_TEACHER_ID + " = ?, " 
					+ COLUMN_PROJECT_ID + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Running select(long id) {
		Running running = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
		
		String query = "SELECT * FROM " + TABLE;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

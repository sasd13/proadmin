/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.member.Teacher;
import com.sasd13.proadmin.core.db.TeacherDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCTeacherDAO extends JDBCTableDAO<Teacher> implements TeacherDAO {
	
	@Override
	protected PreparedStatement getPreparedStatement(String query, Teacher teacher) throws SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(query, teacher);
		
		preparedStatement.setString(1, teacher.getNumber());
		preparedStatement.setString(2, teacher.getFirstName());
		preparedStatement.setString(3, teacher.getLastName());
		preparedStatement.setString(4, teacher.getEmail());
		preparedStatement.setString(5, teacher.getPassword());
		
		return preparedStatement;
	}
	
	@Override
	protected Teacher getResultSetValues(ResultSet resultSet) throws SQLException {
		Teacher teacher = new Teacher();
		
		teacher.setId(resultSet.getLong(TEACHER_ID));
		teacher.setNumber(resultSet.getString(TEACHER_NUMBER));
		teacher.setFirstName(resultSet.getString(TEACHER_FIRSTNAME));
		teacher.setLastName(resultSet.getString(TEACHER_LASTNAME));
		teacher.setEmail(resultSet.getString(TEACHER_EMAIL));
		teacher.setPassword(resultSet.getString(TEACHER_PASSWORD));
		
		return teacher;
	}
	
	@Override
	public long insert(Teacher teacher) {
		long id = 0;
		
		String query = "INSERT INTO " + TEACHER_TABLE_NAME 
				+ "(" 
					+ TEACHER_NUMBER + ", " 
					+ TEACHER_FIRSTNAME + ", " 
					+ TEACHER_LASTNAME + ", " 
					+ TEACHER_EMAIL + ", " 
					+ TEACHER_PASSWORD 
				+ ") VALUES (?, ?, ?, ?, ?)";
		
		try {
			id = executeInsert(query, teacher);
			teacher.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(Teacher teacher) {
		String query = "UPDATE " + TEACHER_TABLE_NAME 
				+ " SET " 
					+ TEACHER_NUMBER + " = ?, " 
					+ TEACHER_FIRSTNAME + " = ?, " 
					+ TEACHER_LASTNAME + " = ?, " 
					+ TEACHER_EMAIL + " = ?, " 
					+ TEACHER_PASSWORD + " = ?, " 
				+ " WHERE " 
					+ TEACHER_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = getPreparedStatement(query, teacher);
			preparedStatement.setLong(6, teacher.getId());
			
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void delete(long id) {
		String query = "UPDATE " + TEACHER_TABLE_NAME 
				+ " SET " 
					+ DELETED + " = ?" 
				+ " WHERE " 
					+ TEACHER_ID + " = ?";
		
		try {
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Teacher select(long id) {
		Teacher teacher = null;
		
		String query = "SELECT * FROM " + TEACHER_TABLE_NAME 
				+ " WHERE " 
					+ TEACHER_ID + " = ?";
		
		try {
			teacher = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teacher;
	}
	
	@Override
	public List<Teacher> selectAll() {
		List<Teacher> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TEACHER_TABLE_NAME;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public Teacher selectByNumber(String number) {
		Teacher teacher = null;
		
		String query = "SELECT * FROM " + TEACHER_TABLE_NAME 
				+ " WHERE " 
					+ TEACHER_NUMBER + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, number);
			
			teacher = executeSelectSingleResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teacher;
	}
	
	@Override
	public List<Teacher> selectByEmail(String email) {
		List<Teacher> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TEACHER_TABLE_NAME 
				+ " WHERE " 
					+ TEACHER_EMAIL + " = ?";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, email);
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

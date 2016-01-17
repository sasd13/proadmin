/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.sasd13.proadmin.core.bean.AcademicLevel;
import com.sasd13.proadmin.core.bean.member.Student;
import com.sasd13.proadmin.core.db.StudentDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samir
 */
public class JDBCStudentDAO extends JDBCEntityDAO<Student> implements StudentDAO {
	
	@Override
	protected PreparedStatement getPreparedStatement(String query, Student student) throws SQLException {
		PreparedStatement preparedStatement = super.getPreparedStatement(query, student);
		
		preparedStatement.setString(1, student.getNumber());
		preparedStatement.setString(2, String.valueOf(student.getAcademicLevel()));
		preparedStatement.setString(3, student.getFirstName());
		preparedStatement.setString(4, student.getLastName());
		preparedStatement.setString(5, student.getEmail());
		
		return preparedStatement;
	}
	
	@Override
	protected Student getResultSetValues(ResultSet resultSet) throws SQLException {
		Student student = new Student();
		
		student.setId(resultSet.getLong(COLUMN_ID));
		student.setNumber(resultSet.getString(COLUMN_NUMBER));
		student.setAcademicLevel(AcademicLevel.valueOf(resultSet.getString(COLUMN_ACADEMICLEVEL)));
		student.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		student.setLastName(resultSet.getString(COLUMN_LASTNAME));
		student.setEmail(resultSet.getString(COLUMN_EMAIL));
		
		return student;
	}
	
	@Override
	public long insert(Student student) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_NUMBER + ", " 
					+ COLUMN_ACADEMICLEVEL + ", " 
					+ COLUMN_FIRSTNAME + ", " 
					+ COLUMN_LASTNAME + ", " 
					+ COLUMN_EMAIL 
				+ ") VALUES (?, ?, ?, ?, ?)";
		
		try {
			id = executeInsert(query, student);
			student.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(Student student) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_NUMBER + " = ?, " 
					+ COLUMN_ACADEMICLEVEL + " = ?, " 
					+ COLUMN_FIRSTNAME + " = ?, " 
					+ COLUMN_LASTNAME + " = ?, " 
					+ COLUMN_EMAIL + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = getPreparedStatement(query, student);
			preparedStatement.setLong(6, student.getId());
			
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
	public Student select(long id) {
		Student student = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		try {
			student = executeSelectById(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	@Override
	public List<Student> selectAll() {
		List<Student> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}

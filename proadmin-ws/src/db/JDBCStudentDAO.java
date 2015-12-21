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
public class JDBCStudentDAO extends JDBCTableDAO<Student> implements StudentDAO {
	
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
		
		student.setId(resultSet.getLong(STUDENT_ID));
		student.setNumber(resultSet.getString(STUDENT_NUMBER));
		student.setAcademicLevel(AcademicLevel.valueOf(resultSet.getString(STUDENT_ACADEMICLEVEL)));
		student.setFirstName(resultSet.getString(STUDENT_FIRSTNAME));
		student.setLastName(resultSet.getString(STUDENT_LASTNAME));
		student.setEmail(resultSet.getString(STUDENT_EMAIL));
		
		return student;
	}
	
	@Override
	public long insert(Student student) {
		long id = 0;
		
		String query = "INSERT INTO " + STUDENT_TABLE_NAME 
				+ "(" 
					+ STUDENT_NUMBER + ", " 
					+ STUDENT_ACADEMICLEVEL + ", " 
					+ STUDENT_FIRSTNAME + ", " 
					+ STUDENT_LASTNAME + ", " 
					+ STUDENT_EMAIL 
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
		String query = "UPDATE " + STUDENT_TABLE_NAME 
				+ " SET " 
					+ STUDENT_NUMBER + " = ?, " 
					+ STUDENT_ACADEMICLEVEL + " = ?, " 
					+ STUDENT_FIRSTNAME + " = ?, " 
					+ STUDENT_LASTNAME + " = ?, " 
					+ STUDENT_EMAIL + " = ?, " 
				+ " WHERE " 
					+ STUDENT_ID + " = ?";
		
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
		String query = "UPDATE " + STUDENT_TABLE_NAME
				+ " SET " 
					+ DELETED + " = ?" 
				+ " WHERE " 
					+ STUDENT_ID + " = ?";
		
		try {			
			executeDelete(query, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Student select(long id) {
		Student student = null;
		
		String query = "SELECT * FROM " + STUDENT_TABLE_NAME 
				+ " WHERE " 
					+ STUDENT_ID + " = ?";
		
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
		
		String query = "SELECT * FROM " + STUDENT_TABLE_NAME;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public Student selectByNumber(String number) {
		Student student = null;
		
		String query = "SELECT * FROM " + STUDENT_TABLE_NAME 
				+ " WHERE " 
					+ STUDENT_NUMBER + " = ?";
		
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, number);
			
			student = executeSelectSingleResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	@Override
	public List<Student> selectByAcademicLevel(AcademicLevel academicLevel) {
		List<Student> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + STUDENT_TABLE_NAME 
				+ " WHERE " 
					+ STUDENT_ACADEMICLEVEL + " = ?";
		
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, String.valueOf(academicLevel));
			
			list = executeSelectMultiResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<Student> selectByEmail(String email) {
		List<Student> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + STUDENT_TABLE_NAME 
				+ " WHERE " 
					+ STUDENT_EMAIL + " = ?";
		
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

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
public class JDBCTeacherDAO extends JDBCEntityDAO<Teacher> implements TeacherDAO {
	
	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Teacher teacher) throws SQLException {
		preparedStatement.setString(1, teacher.getNumber());
		preparedStatement.setString(2, teacher.getFirstName());
		preparedStatement.setString(3, teacher.getLastName());
		preparedStatement.setString(4, teacher.getEmail());
		preparedStatement.setString(5, teacher.getPassword());
	}
	
	@Override
	protected Teacher getResultSetValues(ResultSet resultSet) throws SQLException {
		Teacher teacher = new Teacher();
		
		teacher.setId(resultSet.getLong(COLUMN_ID));
		teacher.setNumber(resultSet.getString(COLUMN_NUMBER));
		teacher.setFirstName(resultSet.getString(COLUMN_FIRSTNAME));
		teacher.setLastName(resultSet.getString(COLUMN_LASTNAME));
		teacher.setEmail(resultSet.getString(COLUMN_EMAIL));
		teacher.setPassword(resultSet.getString(COLUMN_PASSWORD));
		
		return teacher;
	}
	
	@Override
	public long insert(Teacher teacher) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_NUMBER + ", " 
					+ COLUMN_FIRSTNAME + ", " 
					+ COLUMN_LASTNAME + ", " 
					+ COLUMN_EMAIL + ", " 
					+ COLUMN_PASSWORD 
				+ ") VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement preparedStatement = getPreparedStatement(query);
			editPreparedStatement(preparedStatement, teacher);
			
			id = executeInsert(preparedStatement);
			
			teacher.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return id;
	}
	
	@Override
	public void update(Teacher teacher) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_NUMBER + " = ?, " 
					+ COLUMN_FIRSTNAME + " = ?, " 
					+ COLUMN_LASTNAME + " = ?, " 
					+ COLUMN_EMAIL + " = ?, " 
					+ COLUMN_PASSWORD + " = ?" 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
		try {
			PreparedStatement preparedStatement = getPreparedStatement(query);
			editPreparedStatement(preparedStatement, teacher);
			
			preparedStatement.setLong(6, teacher.getId());
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
	public Teacher select(long id) {
		Teacher teacher = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_ID + " = ?";
		
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
		
		String query = "SELECT * FROM " + TABLE;
		
		try {
			list = executeSelectAll(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Teacher selectByNumber(String number) {
		Teacher teacher = null;
		
		String query = "SELECT * FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_NUMBER + " = ?";
		
		try {
			PreparedStatement preparedStatement = getPreparedStatement(query);
			preparedStatement.setString(1, number);
			
			teacher = executeSelectSingleResult(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teacher;
	}
}

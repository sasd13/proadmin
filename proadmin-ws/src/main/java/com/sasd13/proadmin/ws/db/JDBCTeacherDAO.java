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

import com.sasd13.proadmin.bean.member.Teacher;
import com.sasd13.proadmin.dao.TeacherDAO;
import com.sasd13.proadmin.dao.util.WhereClauseParser;

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
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, teacher);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
			}
			
			teacher.setId(id);
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
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			editPreparedStatement(preparedStatement, teacher);
			
			preparedStatement.setLong(6, teacher.getId());
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
			try {
				preparedStatement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Teacher select(long id) {
		Teacher teacher = null;
		
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
					teacher = getResultSetValues(resultSet);
				}
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
		
		return teacher;
	}
	
	public List<Teacher> select(Map<String, String[]> parameters) {
		List<Teacher> list = new ArrayList<>();
		
		Statement statement = null;
		
		try {
			String query = "SELECT * FROM " + TABLE
					+ " WHERE " + WhereClauseParser.parse(TeacherDAO.class, parameters) + ";";
			
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	private void fillListWithResultSet(List<Teacher> list, ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {
			if (!resultSet.getBoolean(COLUMN_DELETED)) {
				list.add(getResultSetValues(resultSet));
			}
		}
	}
	
	@Override
	public List<Teacher> selectAll() {
		List<Teacher> list = new ArrayList<>();
		
		String query = "SELECT * FROM " + TABLE;
		
		Statement statement = null;
		
		try {
			statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(query);
			fillListWithResultSet(list, resultSet);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
}

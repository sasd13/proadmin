package com.sasd13.proadmin.ws.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.IDAO;

public class JDBCPasswordDAO implements IDAO {
	
	private static final String TABLE = "passwords";
	
	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_TEACHER_ID = "teacher_id";
	private static final String COLUMN_DELETED = "deleted";
	
	private Connection connection;
	
	static {
		try {
			Class.forName(JDBCInfo.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void open() throws DAOException {
		try {
			connection = DriverManager.getConnection(JDBCInfo.URL, JDBCInfo.USERNAME, JDBCInfo.PASSWORD);
		} catch (SQLException e) {
			throw new DAOException("Error connection to database");
		}
	}
	
	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public long insert(String password, long teacherId) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_PASSWORD + ", " 
					+ COLUMN_TEACHER_ID
				+ ") VALUES (?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, password);
			preparedStatement.setLong(2, teacherId);
			
			preparedStatement.executeUpdate();
			
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return id;
	}
	
	void update(String password, long teacherId) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_PASSWORD + " = ?"
				+ " WHERE " 
					+ COLUMN_TEACHER_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, password);
			preparedStatement.setLong(2, teacherId);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void delete(long teacherId) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_TEACHER_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, teacherId);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String select(long teacherId) {
		String password = null;
		
		String query = "SELECT " + COLUMN_PASSWORD + " FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_TEACHER_ID + " = ? AND "
					+ COLUMN_DELETED + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, teacherId);
			preparedStatement.setBoolean(2, false);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				password = resultSet.getString(COLUMN_PASSWORD);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return password;
	}
}

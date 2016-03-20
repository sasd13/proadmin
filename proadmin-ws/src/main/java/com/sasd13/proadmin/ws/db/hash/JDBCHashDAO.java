package com.sasd13.proadmin.ws.db.hash;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.IDAO;
import com.sasd13.proadmin.ws.db.JDBCInformation;

public class JDBCHashDAO implements IDAO {
	
	private static final String TABLE = "hashes";
	
	private static final String COLUMN_HASH = "hash";
	private static final String COLUMN_TEACHER_ID = "teacher_id";
	private static final String COLUMN_DELETED = "deleted";
	
	private Connection connection;
	
	static {
		try {
			Class.forName(JDBCInformation.DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void open() throws DAOException {
		try {
			connection = DriverManager.getConnection(JDBCInformation.URL, JDBCInformation.USERNAME, JDBCInformation.PASSWORD);
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
	
	public long insert(String hash, long userId) {
		long id = 0;
		
		String query = "INSERT INTO " + TABLE 
				+ "(" 
					+ COLUMN_HASH + ", " 
					+ COLUMN_TEACHER_ID
				+ ") VALUES (?, ?)";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, hash);
			preparedStatement.setLong(2, userId);
			
			long affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				}
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
	
	void update(String hash, long userId) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_HASH + " = ?"
				+ " WHERE " 
					+ COLUMN_TEACHER_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, hash);
			preparedStatement.setLong(2, userId);
			
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
	
	public void delete(long userId) {
		String query = "UPDATE " + TABLE 
				+ " SET " 
					+ COLUMN_DELETED + " = ?" 
				+ " WHERE " 
					+ COLUMN_TEACHER_ID + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, userId);
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
	
	public String select(long userId) {
		String hash = null;
		
		String query = "SELECT " + COLUMN_HASH + " FROM " + TABLE 
				+ " WHERE " 
					+ COLUMN_TEACHER_ID + " = ? AND "
					+ COLUMN_DELETED + " = ?";
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, userId);
			preparedStatement.setBoolean(2, false);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				hash = resultSet.getString(COLUMN_HASH);
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
		
		return hash;
	}
}

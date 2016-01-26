/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sasd13.javaex.db.IEntityDAO;

/**
 *
 * @author Samir
 */
public abstract class JDBCEntityDAO<T> implements IEntityDAO<T> {
	
	private Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	protected PreparedStatement getPreparedStatement(String query) throws SQLException {
		return (query.startsWith("INSERT") || query.startsWith("insert")) 
				? connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS) 
				: connection.prepareStatement(query);
	}
	
	protected abstract void editPreparedStatement(PreparedStatement preparedStatement, T t) throws SQLException;
	
	protected abstract T getResultSetValues(ResultSet resultSet) throws SQLException;
	
	protected long executeInsert(PreparedStatement preparedStatement) throws SQLException {
		long id = 0;
		
		long affectedRows = preparedStatement.executeUpdate();
		if (affectedRows > 0) {
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			}
		}
		
		preparedStatement.close();
		
		return id;
	}
	
	protected void executeDelete(String query, long id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setBoolean(1, true);
		preparedStatement.setLong(2, id);
		
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	protected T executeSelectById(String query, long id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, id);
		
		return executeSelectSingleResult(preparedStatement);
	}
	
	protected List<T> executeSelectAll(String query) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		
		return executeSelectMultiResult(preparedStatement);
	}
	
	protected T executeSelectSingleResult(PreparedStatement preparedStatement) throws SQLException {
		List<T> list = executeSelectMultiResult(preparedStatement);
		
		return list.isEmpty() ? null : list.get(0);
	}
	
	protected List<T> executeSelectMultiResult(PreparedStatement preparedStatement) throws SQLException {
		List<T> list = new ArrayList<>();
		
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			if (!resultSet.getBoolean(COLUMN_DELETED)) {
				list.add(getResultSetValues(resultSet));
			}
		}
		
		preparedStatement.close();
		
		return list;
	}
}

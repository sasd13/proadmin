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

/**
 *
 * @author Samir
 */
public abstract class JDBCEntityDAO<T> {
	
	protected Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	protected PreparedStatement getPreparedStatement(String query, T t) throws SQLException {
		return (query.startsWith("INSERT") || query.startsWith("insert")) 
				? connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS) 
				: connection.prepareStatement(query);
	}
	
	protected long executeInsert(String query, T t) throws SQLException {
		long id = 0;
		
		PreparedStatement preparedStatement = getPreparedStatement(query, t);
		
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
		T t = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setLong(1, id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if (resultSet.next()) {
			t = getResultSetValues(resultSet);
		}
		
		preparedStatement.close();
		
		return t;
	}
	
	protected List<T> executeSelectAll(String query) throws SQLException {
		return executeSelectMultiResult(connection.prepareStatement(query));
	}
	
	protected T executeSelectSingleResult(PreparedStatement preparedStatement) throws SQLException {
		List<T> list = executeSelectMultiResult(preparedStatement);
		
		return list.isEmpty() ? null : list.get(0);
	}
	
	protected List<T> executeSelectMultiResult(PreparedStatement preparedStatement) throws SQLException {
		List<T> list = new ArrayList<>();
		
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			list.add(getResultSetValues(resultSet));
		}
		
		preparedStatement.close();
		
		return list;
	}
	
	protected abstract T getResultSetValues(ResultSet resultSet) throws SQLException;
}

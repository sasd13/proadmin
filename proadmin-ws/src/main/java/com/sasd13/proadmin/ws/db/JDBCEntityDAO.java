/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sasd13.javaex.db.IEntityDAO;

/**
 *
 * @author Samir
 */
public abstract class JDBCEntityDAO<T> implements IEntityDAO<T> {
	
	protected Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	protected abstract void editPreparedStatement(PreparedStatement preparedStatement, T t) throws SQLException;
	
	protected abstract T getResultSetValues(ResultSet resultSet) throws SQLException;
}

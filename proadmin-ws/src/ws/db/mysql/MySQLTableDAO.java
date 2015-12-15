/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.db.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Samir
 */
public abstract class MySQLTableDAO<T> {
    
    private Connection connection;
    
    protected Connection getConnection() {
        return connection;
    }
    
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    protected PreparedStatement getPreparedStatement(String query, T t) throws SQLException {
        return (query.startsWith("INSERT") || query.startsWith("insert"))
                ? getConnection().prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)
                : getConnection().prepareStatement(query);
    }
    
    protected abstract T getResultSetValues(ResultSet resultSet) throws SQLException;
}

package com.sasd13.proadmin.aaa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.ConditionBuilder;
import com.sasd13.javaex.dao.ConditionException;
import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.jdbc.IJDBCChecker;
import com.sasd13.javaex.dao.jdbc.IJDBCManager;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.security.Credential;

public class JDBCCredentialDAO implements ICredentialDAO, IJDBCManager<Credential>, IJDBCChecker<Credential> {

	private static final Logger LOG = Logger.getLogger(JDBCCredentialDAO.class);

	private String url, username, password;
	private Connection connection;
	private Map<String, String[]> parameters;
	private IExpressionBuilder expressionBuilder;

	public JDBCCredentialDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		parameters = new HashMap<>();
		expressionBuilder = new CredentialExpressionBuilder();
	}

	@Override
	public void open() throws DAOException {
		try {
			setConnection(DriverManager.getConnection(url, username, password));
		} catch (SQLException e) {
			LOG.error(e);
			throw new DAOException("Database connection error");
		}
	}

	@Override
	public void close() {
		if (getConnection() != null) {
			try {
				getConnection().close();
			} catch (SQLException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public long insert(Credential credential) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_USERNAME);
		builder.append(", " + COLUMN_PASSWORD);
		builder.append(") VALUES (?, ?)");

		return JDBCUtils.insert(this, builder.toString(), credential);
	}

	@Override
	public void update(Credential credential) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_USERNAME + " = ?");
		builder.append(" AND " + COLUMN_PASSWORD + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");

		JDBCUtils.update(this, builder.toString(), credential);
	}

	@Override
	public void delete(Credential credential) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");

		JDBCUtils.delete(this, builder.toString(), credential);
	}

	@Override
	public boolean contains(Credential credential) throws DAOException {
		return JDBCUtils.contains(this, TABLE, credential);
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		preparedStatement.setString(1, credential.getUsername());
		preparedStatement.setString(2, credential.getPassword());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, credential);

		preparedStatement.setString(3, credential.getUsername());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		preparedStatement.setString(1, credential.getUsername());
	}

	@Override
	public String getCondition(Credential credential) throws ConditionException {
		parameters.clear();
		parameters.put(COLUMN_USERNAME, new String[] { credential.getUsername() });
		parameters.put(COLUMN_PASSWORD, new String[] { credential.getPassword() });

		return ConditionBuilder.parse(parameters, expressionBuilder);
	}
}

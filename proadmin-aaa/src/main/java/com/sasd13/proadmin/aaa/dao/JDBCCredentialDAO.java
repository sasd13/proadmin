package com.sasd13.proadmin.aaa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.JDBCCheckerDAO;
import com.sasd13.javaex.dao.JDBCUtils;
import com.sasd13.javaex.security.Credential;

public class JDBCCredentialDAO extends JDBCCheckerDAO<Credential> implements ICredentialDAO {

	private static final Logger LOG = Logger.getLogger(JDBCCredentialDAO.class);

	private String url, username, password;
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
			Connection connection = DriverManager.getConnection(url, username, password);

			setConnection(connection);
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
		parameters.clear();
		parameters.put(COLUMN_USERNAME, new String[] { credential.getUsername() });
		parameters.put(COLUMN_PASSWORD, new String[] { credential.getPassword() });

		return JDBCUtils.contains(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public void editPreparedStatement(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		preparedStatement.setString(1, credential.getUsername());
		preparedStatement.setString(2, credential.getPassword());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		super.editPreparedStatementForUpdate(preparedStatement, credential);

		preparedStatement.setString(3, credential.getUsername());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		super.editPreparedStatementForDelete(preparedStatement, credential);

		preparedStatement.setString(1, credential.getUsername());
	}

	@Override
	public Credential getResultSetValues(ResultSet resultSet) throws SQLException {
		Credential credential = new Credential(resultSet.getString(COLUMN_USERNAME), resultSet.getString(COLUMN_PASSWORD));

		return credential;
	}
}

package com.sasd13.proadmin.aaa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.security.Credential;

public class JDBCCredentialDAO implements ICredentialDAO {

	private static final Logger LOG = Logger.getLogger(JDBCCredentialDAO.class);

	private String url, username, password;
	private Connection connection;

	public JDBCCredentialDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	@Override
	public void open() throws DAOException {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			LOG.error(e);
			throw new DAOException("Database connection error");
		}
	}

	@Override
	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public long insert(Credential credential) throws DAOException {
		LOG.info("insert : username=" + credential.getUsername());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_USERNAME);
		builder.append(", " + COLUMN_PASSWORD);
		builder.append(") VALUES (?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, credential.getUsername());
			preparedStatement.setString(2, credential.getPassword());

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatch("insert failed", "Credential not inserted");
		} finally {
			doFinally(preparedStatement);
		}

		return id;
	}

	private void doCatch(String logMessage, String exceptionMessage) throws DAOException {
		LOG.error(logMessage);
		throw new DAOException(exceptionMessage);
	}

	private void doFinally(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOG.warn(e);
			}
		}
	}

	@Override
	public void update(Credential credential) throws DAOException {
		LOG.info("update : username=" + credential.getUsername());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_PASSWORD + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, credential.getPassword());
			preparedStatement.setString(2, credential.getUsername());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch("update failed", "Credential not updated");
		} finally {
			doFinally(preparedStatement);
		}
	}

	@Override
	public void delete(Credential credential) throws DAOException {
		LOG.info("delete : username=" + credential.getUsername());

		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, credential.getUsername());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch("delete failed", "Credential not deleted");
		} finally {
			doFinally(preparedStatement);
		}
	}

	@Override
	public boolean contains(Credential credential) throws DAOException {
		LOG.info("check : username=" + credential.getUsername());

		boolean contains = false;

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT " + COLUMN_PASSWORD + " FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");
		builder.append(" AND ");
		builder.append(COLUMN_PASSWORD + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, credential.getUsername());
			preparedStatement.setString(2, credential.getPassword());

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				contains = true;
			}
		} catch (SQLException e) {
			doCatch("check failed", "Credential not checked");
		} finally {
			doFinally(preparedStatement);
		}

		return contains;
	}
}

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
			LOG.error("JDBCCredentialDAO --> open failed", e);
			throw new DAOException("Database connection not opened");
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
		LOG.info("JDBCCredentialDAO --> insert : username=" + credential.getUsername());

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
			doCatch(e, "JDBCCredentialDAO --> insert failed", "Credential not inserted");
		} finally {
			doFinally(preparedStatement);
		}

		return id;
	}

	private void doCatch(Exception e, String logErrorMessage, String exceptionErrorMessage) throws DAOException {
		LOG.error(logErrorMessage, e);
		throw new DAOException(exceptionErrorMessage);
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
		LOG.info("JDBCCredentialDAO --> update : username=" + credential.getUsername());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_USERNAME + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_PASSWORD + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, credential.getUsername());
			preparedStatement.setString(2, credential.getPassword());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, "JDBCCredentialDAO --> update failed", "Credential not updated");
		} finally {
			doFinally(preparedStatement);
		}
	}

	@Override
	public void delete(Credential credential) throws DAOException {
		LOG.info("JDBCCredentialDAO --> delete : username=" + credential.getUsername());

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
			doCatch(e, "JDBCCredentialDAO --> delete failed", "Credential not deleted");
		} finally {
			doFinally(preparedStatement);
		}
	}

	@Override
	public boolean contains(Credential credential) throws DAOException {
		LOG.info("JDBCCredentialDAO --> contains : username=" + credential.getUsername());

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
			doCatch(e, "JDBCCredentialDAO --> contains failed", "Credential not checked");
		} finally {
			doFinally(preparedStatement);
		}

		return contains;
	}
}

package com.sasd13.proadmin.aaa.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sasd13.javaex.db.DAOException;
import com.sasd13.proadmin.aaa.Config;
import com.sasd13.proadmin.aaa.Credential;
import com.sasd13.proadmin.aaa.Infra;
import com.sasd13.proadmin.aaa.dao.CredentialDAO;

public class JDBCCredentialDAO implements CredentialDAO {

	private static String url, username, password;

	private Connection connection;

	static {
		try {
			Class.forName(Config.getInfo(Infra.DB_DRIVER));

			JDBCCredentialDAO.url = Config.getInfo(Infra.DB_URL);
			JDBCCredentialDAO.username = Config.getInfo(Infra.DB_USERNAME);
			JDBCCredentialDAO.password = Config.getInfo(Infra.DB_PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void open() throws DAOException {
		try {
			connection = DriverManager.getConnection(url, username, password);
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

	@Override
	public boolean insert(Credential credential) {
		boolean inserted = false;

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
				inserted = true;
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

		return inserted;
	}

	@Override
	public boolean update(Credential credential) {
		int rowCount = 0;
		
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

			rowCount = preparedStatement.executeUpdate();
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
		
		return rowCount > 0;
	}

	@Override
	public boolean delete(String username) {
		int rowCount = 0;
		
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, username);

			rowCount = preparedStatement.executeUpdate();
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
		
		return rowCount > 0;
	}

	@Override
	public boolean contains(Credential credential) {
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

		return contains;
	}
}

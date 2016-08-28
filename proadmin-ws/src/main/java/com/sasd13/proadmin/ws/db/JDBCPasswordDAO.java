package com.sasd13.proadmin.ws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCPasswordDAO {

	private static final String TABLE = "passwords";

	private static final String COLUMN_PASSWORD = "password";
	private static final String COLUMN_TEACHER_ID = "teacher_id";
	private static final String COLUMN_DELETED = "deleted";

	private Connection connection;

	public JDBCPasswordDAO(Connection connection) {
		this.connection = connection;
	}

	public long insert(String password, long teacherId) {
		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_PASSWORD);
		builder.append(", " + COLUMN_TEACHER_ID);
		builder.append(") VALUES (?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, password);
			preparedStatement.setLong(2, teacherId);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
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

	void update(String password, long teacherId) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_PASSWORD + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_TEACHER_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, password);
			preparedStatement.setLong(2, teacherId);

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

	public void delete(long teacherId) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DELETED + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_TEACHER_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setBoolean(1, true);
			preparedStatement.setLong(2, teacherId);

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

	public boolean contains(String password, long teacherId) {
		boolean contains = false;

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT " + COLUMN_PASSWORD + " FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_PASSWORD + " = ?");
		builder.append(" AND ");
		builder.append(COLUMN_TEACHER_ID + " = ?");
		builder.append(" AND ");
		builder.append(COLUMN_DELETED + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, password);
			preparedStatement.setLong(2, teacherId);
			preparedStatement.setBoolean(3, false);

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

package com.sasd13.proadmin.ws.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCQualificationDAO {

	private static final String TABLE = "qualifications";

	private static final String COLUMN_QUALIFICATION = "qualification";
	private static final String COLUMN_TEACHER_ID = "teacher_id";

	private Connection connection;

	public JDBCQualificationDAO(Connection connection) {
		this.connection = connection;
	}

	public boolean insert(String qualification, long teacherId) {
		boolean inserted = false;
		
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_QUALIFICATION);
		builder.append(", " + COLUMN_TEACHER_ID);
		builder.append(") VALUES (?, ?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, qualification);
			preparedStatement.setLong(2, teacherId);

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

	public void update(String qualification, long teacherId) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_QUALIFICATION + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_TEACHER_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, qualification);
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
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_TEACHER_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setLong(1, teacherId);

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

	public boolean contains(String qualification, long teacherId) {
		boolean contains = false;

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT " + COLUMN_QUALIFICATION + " FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_QUALIFICATION + " = ?");
		builder.append(" AND ");
		builder.append(COLUMN_TEACHER_ID + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, qualification);
			preparedStatement.setLong(2, teacherId);

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

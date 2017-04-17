package com.sasd13.proadmin.aaa.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.conf.AppProperties;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.util.condition.ConditionBuilder;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.condition.IConditionnal;
import com.sasd13.proadmin.aaa.dao.IUserDAO;
import com.sasd13.proadmin.aaa.util.Names;
import com.sasd13.proadmin.bean.user.User;
import com.sasd13.proadmin.util.EnumParameter;

public class JDBCUserDAO extends JDBCSession implements IUserDAO, IConditionnal {

	private static final Logger LOGGER = Logger.getLogger(JDBCUserDAO.class);
	private static final String SEPARATOR = AppProperties.getProperty(Names.AAA_ROLES_SEPARATOR);

	@Override
	public long create(User user, Credential credential) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_USERID);
		builder.append(", " + COLUMN_USERNAME);
		builder.append(", " + COLUMN_PASSWORD);
		builder.append(", " + COLUMN_ROLES);
		builder.append(", " + COLUMN_INTERMEDIARY);
		builder.append(", " + COLUMN_EMAIL);
		builder.append(") VALUES (?, ?, ?, ?, ?, ?)");

		long id = 0;

		PreparedStatement statement = null;

		try {
			statement = getConnection().prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUserID());
			statement.setString(2, credential.getUsername());
			statement.setString(3, credential.getPassword());
			statement.setString(4, String.join(SEPARATOR, user.getRoles()));
			statement.setString(5, user.getIntermediary());
			statement.setString(6, user.getEmail());

			statement.executeUpdate();

			ResultSet generatedKeys = statement.getGeneratedKeys();

			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				LOGGER.error("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.warn(e);
				}
			}
		}

		return id;
	}

	@Override
	public void update(User user, Credential credential) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_STATUS + " = ?");
		builder.append(" AND " + COLUMN_ROLES + " = ?");
		builder.append(" AND " + COLUMN_INTERMEDIARY + " = ?");
		builder.append(" AND " + COLUMN_EMAIL + " = ?");

		if (credential != null) {
			builder.append(" AND " + COLUMN_USERNAME + " = ?");
			builder.append(" AND " + COLUMN_PASSWORD + " = ?");
		}

		builder.append(" WHERE ");
		builder.append(COLUMN_USERID + " = ?");

		PreparedStatement statement = null;

		try {
			statement = getConnection().prepareStatement(builder.toString());
			statement.setInt(1, user.getStatus().getCode());
			statement.setString(2, String.join(SEPARATOR, user.getRoles()));
			statement.setString(3, user.getIntermediary());
			statement.setString(4, user.getEmail());

			if (credential != null) {
				statement.setString(5, credential.getUsername());
				statement.setString(6, credential.getPassword());
				statement.setString(7, user.getUserID());
			} else {
				statement.setString(5, user.getUserID());
			}

			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.warn(e);
				}
			}
		}
	}

	@Override
	public User find(Credential credential) {
		User user = null;

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");
		builder.append(" AND " + COLUMN_PASSWORD + " = ?");

		PreparedStatement statement = null;

		try {
			statement = getConnection().prepareStatement(builder.toString());
			statement.setString(1, credential.getUsername());
			statement.setString(2, credential.getPassword());

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.warn(e);
				}
			}
		}

		return user;
	}

	private User getResultSetValues(ResultSet resultSet) throws SQLException {
		User user = new User();

		user.setUserID(resultSet.getString(COLUMN_USERID));
		user.setRoles(resultSet.getString(COLUMN_ROLES).split(SEPARATOR));
		user.setIntermediary(resultSet.getString(COLUMN_INTERMEDIARY));
		user.setEmail(resultSet.getString(COLUMN_EMAIL));

		return user;
	}

	@Override
	public User find(String userID) {
		User user = null;

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_USERID + " = ?");

		PreparedStatement statement = null;

		try {
			statement = getConnection().prepareStatement(builder.toString());
			statement.setString(1, userID);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				user = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.warn(e);
				}
			}
		}

		return user;
	}

	@Override
	public List<User> read(Map<String, String[]> parameters) {
		List<User> list = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");

		PreparedStatement statement = null;

		try {
			builder.append(ConditionBuilder.build(parameters, this));

			statement = getConnection().prepareStatement(builder.toString());

			int i = 0;

			for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
				for (String value : entry.getValue()) {
					editPreparedStatementForSelect(statement, ++i, entry.getKey(), value);
				}
			}

			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				list.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					LOGGER.warn(e);
				}
			}
		}

		return list;
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.USERID.getName().equalsIgnoreCase(key)) {
			return IUserDAO.COLUMN_USERID + " = ?";
		} else if (EnumParameter.INTERMEDIARY.getName().equalsIgnoreCase(key)) {
			return IUserDAO.COLUMN_INTERMEDIARY + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	private void editPreparedStatementForSelect(PreparedStatement statement, int index, String key, String value) throws SQLException {
		if (EnumParameter.USERID.getName().equalsIgnoreCase(key)) {
			statement.setString(index, value);
		} else if (EnumParameter.INTERMEDIARY.getName().equalsIgnoreCase(key)) {
			statement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}
}

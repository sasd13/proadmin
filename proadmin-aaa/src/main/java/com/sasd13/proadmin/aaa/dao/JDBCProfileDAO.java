package com.sasd13.proadmin.aaa.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.security.Credential;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.aaa.bean.Profile;

public class JDBCProfileDAO extends JDBCSession<Profile> implements IProfileDAO {

	private static final Logger LOGGER = Logger.getLogger(JDBCProfileDAO.class);
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	private String url;
	private Properties properties;

	public JDBCProfileDAO(String url, Properties properties) {
		this.url = url;
		this.properties = properties;
	}

	@Override
	public void open() {
		try {
			setConnection(DriverManager.getConnection(url, properties));
		} catch (SQLException e) {
			LOGGER.error(e);
			throw new DAOException("Database connection error");
		}
	}

	@Override
	public void close() {
		if (getConnection() != null) {
			try {
				getConnection().close();
			} catch (SQLException e) {
				LOGGER.warn(e);
			}
		}
	}

	@Override
	public long insert(Profile profile) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_USERNAME);
		builder.append(", " + COLUMN_PASSWORD);
		builder.append(", " + COLUMN_CODE);
		builder.append(") VALUES (?, ?)");

		return JDBCUtils.insert(this, builder.toString(), profile);
	}

	@Override
	public void update(IUpdateWrapper<Profile> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_PASSWORD + " = ?");
		builder.append(" AND " + COLUMN_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public List<Profile> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Profile profile) throws SQLException {
		preparedStatement.setString(1, profile.getUsername());
		preparedStatement.setString(2, profile.getPassword());
		preparedStatement.setString(2, profile.getNumber());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Profile> updateWrapper) throws SQLException {
		Profile profile = updateWrapper.getWrapped();

		preparedStatement.setString(1, profile.getPassword());
		preparedStatement.setString(2, profile.getNumber());
		preparedStatement.setString(3, profile.getUsername());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (USERNAME.equalsIgnoreCase(key)) {
			return IProfileDAO.COLUMN_USERNAME + " = ?";
		} else if (PASSWORD.equalsIgnoreCase(key)) {
			return IProfileDAO.COLUMN_PASSWORD + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (USERNAME.equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (PASSWORD.equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public Profile getResultSetValues(ResultSet resultSet) throws SQLException {
		Profile profile = new Profile(new Credential());

		profile.setUsername(resultSet.getString(COLUMN_USERNAME));
		profile.setNumber(resultSet.getString(COLUMN_CODE));

		return profile;
	}
}

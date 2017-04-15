package com.sasd13.proadmin.aaa.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
import com.sasd13.proadmin.util.wrapper.update.credential.CredentialUpdateWrapper;

public class JDBCCredentialDAO extends JDBCSession<Credential> implements ICredentialDAO {

	private static final Logger LOGGER = Logger.getLogger(JDBCCredentialDAO.class);
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";

	private String url;
	private Properties properties;
	private Map<String, String[]> parameters;

	public JDBCCredentialDAO(String url, Properties properties) {
		this.url = url;
		this.properties = properties;
		parameters = new HashMap<>();
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
	public long insert(Credential credential) {
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
	public void update(IUpdateWrapper<Credential> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_USERNAME + " = ?");
		builder.append(" AND " + COLUMN_PASSWORD + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Credential credential) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_USERNAME + " = ?");

		JDBCUtils.delete(this, builder.toString(), credential);
	}

	@Override
	public boolean contains(Credential credential) {
		parameters.clear();
		parameters.put(USERNAME, new String[] { credential.getUsername() });
		parameters.put(PASSWORD, new String[] { credential.getPassword() });

		return JDBCUtils.contains(this, TABLE, parameters);
	}

	@Override
	public Credential select(long id) {
		return null;
	}

	@Override
	public List<Credential> select(Map<String, String[]> parameters) {
		return null;
	}

	@Override
	public List<Credential> selectAll() {
		return null;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		preparedStatement.setString(1, credential.getUsername());
		preparedStatement.setString(2, credential.getPassword());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Credential> updateWrapper) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, updateWrapper.getWrapped());

		preparedStatement.setString(3, ((CredentialUpdateWrapper) updateWrapper).getUsername());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		preparedStatement.setString(1, credential.getUsername());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (USERNAME.equalsIgnoreCase(key)) {
			return ICredentialDAO.COLUMN_USERNAME + " = ?";
		} else if (PASSWORD.equalsIgnoreCase(key)) {
			return ICredentialDAO.COLUMN_PASSWORD + " = ?";
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
	public Credential getResultSetValues(ResultSet resultSet) throws SQLException {
		return null;
	}
}

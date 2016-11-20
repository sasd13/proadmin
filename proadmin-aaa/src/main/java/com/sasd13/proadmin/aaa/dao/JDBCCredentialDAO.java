package com.sasd13.proadmin.aaa.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IUpdateWrapper;
import com.sasd13.javaex.dao.jdbc.ConditionException;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.security.Credential;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.credential.ICredentialUpdateWrapper;

public class JDBCCredentialDAO extends JDBCSession<Credential> implements ICredentialDAO {

	private static final Logger LOGGER = Logger.getLogger(JDBCCredentialDAO.class);

	private String url, username, password;
	private Map<String, String[]> parameters;

	public JDBCCredentialDAO(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
		parameters = new HashMap<>();
	}

	@Override
	public void open() throws DAOException {
		try {
			setConnection(DriverManager.getConnection(url, username, password));
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
	public void update(IUpdateWrapper<Credential> updateWrapper) throws DAOException {
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
		parameters.put(EnumParameter.USERNAME.getName(), new String[] { credential.getUsername() });
		parameters.put(EnumParameter.PASSWORD.getName(), new String[] { credential.getPassword() });

		return JDBCUtils.contains(this, TABLE, parameters);
	}

	@Override
	public Credential select(long id) throws DAOException {
		return null;
	}

	@Override
	public List<Credential> select(Map<String, String[]> parameters) throws DAOException {
		return null;
	}

	@Override
	public List<Credential> selectAll() throws DAOException {
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

		preparedStatement.setString(3, ((ICredentialUpdateWrapper) updateWrapper).getUsername());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Credential credential) throws SQLException {
		preparedStatement.setString(1, credential.getUsername());
	}

	@Override
	public String buildCondition(String key) throws ConditionException {
		if (EnumParameter.USERNAME.getName().equalsIgnoreCase(key)) {
			return ICredentialDAO.COLUMN_USERNAME;
		} else if (EnumParameter.PASSWORD.getName().equalsIgnoreCase(key)) {
			return ICredentialDAO.COLUMN_PASSWORD;
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (EnumParameter.USERNAME.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.PASSWORD.getName().equalsIgnoreCase(key)) {
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

package com.sasd13.proadmin.aaa.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.NotImplementedException;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.aaa.EnumParameter;
import com.sasd13.proadmin.aaa.dao.IProfileDAO;
import com.sasd13.proadmin.bean.profile.Profile;
import com.sasd13.proadmin.util.wrapper.profile.ProfileUpdateWrapper;

public class JDBCProfileDAO extends JDBCSession<Profile> implements IProfileDAO {

	private static final String SEPARATOR = ";";

	@Override
	public long create(Profile profile) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_USERID);
		builder.append(", " + COLUMN_ROLES);
		builder.append(", " + COLUMN_INTERMEDIARY);
		builder.append(", " + COLUMN_EMAIL);
		builder.append(") VALUES (?, ?, ?, ?)");

		return JDBCUtils.insert(this, builder.toString(), profile);
	}

	@Override
	public void update(ProfileUpdateWrapper updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_ROLES + " = ?");
		builder.append("AND " + COLUMN_INTERMEDIARY + " = ?");
		builder.append("AND " + COLUMN_EMAIL + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_USERID + " = ?");

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public List<Profile> read(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Profile> readAll() {
		throw new NotImplementedException("Not implemented");
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Profile profile) throws SQLException {
		preparedStatement.setString(1, profile.getUserID());
		preparedStatement.setString(2, String.join(SEPARATOR, profile.getRoles()));
		preparedStatement.setString(3, profile.getIntermediary());
		preparedStatement.setString(4, profile.getEmail());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Profile> updateWrapper) throws SQLException {
		Profile profile = updateWrapper.getWrapped();

		preparedStatement.setString(1, String.join(SEPARATOR, profile.getRoles()));
		preparedStatement.setString(2, profile.getIntermediary());
		preparedStatement.setString(3, profile.getEmail());
		preparedStatement.setString(4, ((ProfileUpdateWrapper) updateWrapper).getUserID());
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (EnumParameter.USERNAME.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.USERID.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public Profile getResultSetValues(ResultSet resultSet) throws SQLException {
		Profile profile = new Profile();

		profile.setUserID(resultSet.getString(COLUMN_USERID));
		profile.setRoles(resultSet.getString(COLUMN_ROLES).split(SEPARATOR));
		profile.setIntermediary(resultSet.getString(COLUMN_INTERMEDIARY));
		profile.setEmail(resultSet.getString(COLUMN_EMAIL));

		return profile;
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.ws.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.ITeam;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.ws.bean.update.TeamUpdate;
import com.sasd13.proadmin.ws.dao.ITeamDAO;

/**
 *
 * @author Samir
 */
public class JDBCTeamDAO extends JDBCSession<ITeam> implements ITeamDAO {

	@Override
	public long create(ITeam iTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_NAME);
		builder.append(") VALUES (?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), iTeam);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(TeamUpdate updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_NAME + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.update(this, builder.toString(), updateWrapper);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(ITeam iTeam) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), iTeam);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ITeam> read(Map<String, String[]> parameters) {
		try {
			return JDBCUtils.select(this, TABLE, parameters);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ITeam> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, ITeam iTeam) throws SQLException {
		preparedStatement.setString(1, iTeam.getName());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<ITeam> updateWrapper) throws SQLException {
		ITeam iTeam = updateWrapper.getWrapped();

		preparedStatement.setString(1, iTeam.getName());
		preparedStatement.setString(2, ((TeamUpdate) updateWrapper).getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, ITeam iTeam) throws SQLException {
		preparedStatement.setString(1, iTeam.getNumber());
	}

	@Override
	public String getCondition(String key) {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return ITeamDAO.COLUMN_CODE + " = ?";
		} else if (EnumParameter.NAME.getName().equalsIgnoreCase(key)) {
			return ITeamDAO.COLUMN_NAME + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumParameter.NAME.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public ITeam getResultSetValues(ResultSet resultSet) throws SQLException {
		ITeam iTeam = new ITeam();

		iTeam.setNumber(resultSet.getString(COLUMN_CODE));
		iTeam.setName(resultSet.getString(COLUMN_NAME));

		return iTeam;
	}
}

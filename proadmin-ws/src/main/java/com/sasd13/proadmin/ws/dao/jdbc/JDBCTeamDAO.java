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
import com.sasd13.proadmin.util.EnumCriteria;
import com.sasd13.proadmin.ws.bean.Team;
import com.sasd13.proadmin.ws.bean.update.TeamUpdate;
import com.sasd13.proadmin.ws.dao.ITeamDAO;

/**
 *
 * @author Samir
 */
public class JDBCTeamDAO extends JDBCSession<Team> implements ITeamDAO {

	@Override
	public long create(Team team) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_NAME);
		builder.append(") VALUES (?)");

		try {
			return JDBCUtils.insert(this, builder.toString(), team);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(TeamUpdate teamUpdate) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_NAME + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.update(this, builder.toString(), teamUpdate);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Team team) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		try {
			JDBCUtils.delete(this, builder.toString(), team);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Team> read(Map<String, String[]> criterias) {
		try {
			return JDBCUtils.select(this, TABLE, criterias);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Team> readAll() {
		try {
			return JDBCUtils.selectAll(this, TABLE);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Team team) throws SQLException {
		preparedStatement.setString(1, team.getName());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Team> updateWrapper) throws SQLException {
		Team team = updateWrapper.getWrapped();

		preparedStatement.setString(1, team.getName());
		preparedStatement.setString(2, ((TeamUpdate) updateWrapper).getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Team team) throws SQLException {
		preparedStatement.setString(1, team.getNumber());
	}

	@Override
	public String getCondition(String key, int index) {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			return COLUMN_CODE + " = ?";
		} else if (EnumCriteria.NAME.getCode().equalsIgnoreCase(key)) {
			return COLUMN_NAME + " = ?";
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException {
		if (EnumCriteria.NUMBER.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else if (EnumCriteria.NAME.getCode().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Criteria " + key + " is unknown");
		}
	}

	@Override
	public Team getResultSetValues(ResultSet resultSet) throws SQLException {
		Team team = new Team();

		team.setNumber(resultSet.getString(COLUMN_CODE));
		team.setName(resultSet.getString(COLUMN_NAME));

		return team;
	}
}

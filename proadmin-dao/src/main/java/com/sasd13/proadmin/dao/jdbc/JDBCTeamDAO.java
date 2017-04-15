/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.javaex.util.condition.ConditionException;
import com.sasd13.javaex.util.wrapper.IUpdateWrapper;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.dao.ITeamDAO;
import com.sasd13.proadmin.util.EnumParameter;
import com.sasd13.proadmin.util.wrapper.update.member.TeamUpdateWrapper;

/**
 *
 * @author Samir
 */
public class JDBCTeamDAO extends JDBCSession<Team> implements ITeamDAO {

	@Override
	public long insert(Team team) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(") VALUES (?)");

		return JDBCUtils.insert(this, builder.toString(), team);
	}

	@Override
	public void update(IUpdateWrapper<Team> updateWrapper) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.update(this, builder.toString(), updateWrapper);
	}

	@Override
	public void delete(Team team) {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), team);
	}

	@Override
	public Team select(long id) {
		return null;
	}

	@Override
	public List<Team> select(Map<String, String[]> parameters) {
		return JDBCUtils.select(this, TABLE, parameters);
	}

	@Override
	public List<Team> selectAll() {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public boolean contains(Team team) {
		return false;
	}

	@Override
	public void editPreparedStatementForInsert(PreparedStatement preparedStatement, Team team) throws SQLException {
		preparedStatement.setString(1, team.getNumber());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, IUpdateWrapper<Team> updateWrapper) throws SQLException {
		editPreparedStatementForInsert(preparedStatement, updateWrapper.getWrapped());

		preparedStatement.setString(2, ((TeamUpdateWrapper) updateWrapper).getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Team team) throws SQLException {
		preparedStatement.setString(1, team.getNumber());
	}

	@Override
	public String getCondition(String key) throws ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			return ITeamDAO.COLUMN_CODE + " = ?";
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public void editPreparedStatementForSelect(PreparedStatement preparedStatement, int index, String key, String value) throws SQLException, ConditionException {
		if (EnumParameter.NUMBER.getName().equalsIgnoreCase(key)) {
			preparedStatement.setString(index, value);
		} else {
			throw new ConditionException("Parameter " + key + " is unknown");
		}
	}

	@Override
	public Team getResultSetValues(ResultSet resultSet) throws SQLException {
		Team team = new Team();

		team.setNumber(resultSet.getString(COLUMN_CODE));

		return team;
	}
}

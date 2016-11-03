/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.IExpressionBuilder;
import com.sasd13.javaex.dao.jdbc.JDBCSession;
import com.sasd13.javaex.dao.jdbc.JDBCUtils;
import com.sasd13.proadmin.bean.member.Team;

/**
 *
 * @author Samir
 */
public class JDBCTeamDAO extends JDBCSession<Team> implements ITeamDAO {

	private static final Logger LOG = Logger.getLogger(JDBCTeamDAO.class);

	private IExpressionBuilder expressionBuilder;

	public JDBCTeamDAO() {
		expressionBuilder = new TeamExpressionBuilder();
	}

	@Override
	public long insert(Team team) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(") VALUES (?)");

		return JDBCUtils.insert(this, builder.toString(), team);
	}

	@Override
	public void update(Team team) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.update(this, builder.toString(), team);
	}

	@Override
	public void delete(Team team) throws DAOException {
		StringBuilder builder = new StringBuilder();
		builder.append("DELETE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		JDBCUtils.delete(this, builder.toString(), team);
	}

	@Override
	public Team select(long id) throws DAOException {
		LOG.error("select unavailable");
		throw new DAOException("Request unavailable");
	}

	@Override
	public List<Team> select(Map<String, String[]> parameters) throws DAOException {
		return JDBCUtils.select(this, TABLE, parameters, expressionBuilder);
	}

	@Override
	public List<Team> selectAll() throws DAOException {
		return JDBCUtils.selectAll(this, TABLE);
	}

	@Override
	public void editPreparedStatement(PreparedStatement preparedStatement, Team team) throws SQLException {
		preparedStatement.setString(1, team.getNumber());
	}

	@Override
	public void editPreparedStatementForUpdate(PreparedStatement preparedStatement, Team team) throws SQLException {
		super.editPreparedStatementForUpdate(preparedStatement, team);

		preparedStatement.setString(2, team.getNumber());
	}

	@Override
	public void editPreparedStatementForDelete(PreparedStatement preparedStatement, Team team) throws SQLException {
		super.editPreparedStatementForDelete(preparedStatement, team);

		preparedStatement.setString(1, team.getNumber());
	}

	@Override
	public Team getResultSetValues(ResultSet resultSet) throws SQLException {
		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_CODE));

		return team;
	}
}

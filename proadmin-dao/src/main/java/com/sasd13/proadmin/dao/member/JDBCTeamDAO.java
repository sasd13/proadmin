/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao.member;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sasd13.javaex.dao.DAOException;
import com.sasd13.javaex.dao.JDBCEntityDAO;
import com.sasd13.javaex.dao.condition.ConditionBuilder;
import com.sasd13.javaex.net.http.URLQueryUtils;
import com.sasd13.proadmin.bean.member.Team;
import com.sasd13.proadmin.util.dao.validator.ValidatorUtils;

/**
 *
 * @author Samir
 */
public class JDBCTeamDAO extends JDBCEntityDAO<Team> implements ITeamDAO {

	private static final Logger LOG = Logger.getLogger(JDBCTeamDAO.class);

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, Team team) throws SQLException {
		preparedStatement.setString(1, team.getNumber());
	}

	@Override
	protected Team getResultSetValues(ResultSet resultSet) throws SQLException {
		Team team = new Team();
		team.setNumber(resultSet.getString(COLUMN_CODE));

		return team;
	}

	@Override
	public long insert(Team team) throws DAOException {
		ValidatorUtils.validate(team);

		LOG.info("JDBCTeamDAO --> insert : number=" + team.getNumber());

		long id = 0;

		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(TABLE);
		builder.append("(");
		builder.append(COLUMN_CODE);
		builder.append(") VALUES (?)");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString(), PreparedStatement.RETURN_GENERATED_KEYS);
			editPreparedStatement(preparedStatement, team);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCTeamDAO --> insert failed", "Team not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(Team team) throws DAOException {
		ValidatorUtils.validate(team);

		LOG.info("JDBCTeamDAO --> update : number=" + team.getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_CODE + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			editPreparedStatement(preparedStatement, team);
			preparedStatement.setString(2, team.getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCTeamDAO --> update failed", "Team not updated");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public void delete(Team team) throws DAOException {
		ValidatorUtils.validate(team);

		LOG.info("JDBCTeamDAO --> delete : number=" + team.getNumber());

		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE ");
		builder.append(TABLE);
		builder.append(" SET ");
		builder.append(COLUMN_DELETED + " = ?");
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setBoolean(1, true);
			preparedStatement.setString(2, team.getNumber());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCTeamDAO --> delete failed", "Team not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public Team select(long id) throws DAOException {
		LOG.info("JDBCTeamDAO --> select : id=" + id);

		Team team = null;

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_ID + " = ?");
		builder.append(" AND ");
		builder.append(COLUMN_DELETED + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setLong(1, id);
			preparedStatement.setBoolean(2, false);

			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				team = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCTeamDAO --> select failed", "Team not readed");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return team;
	}

	public List<Team> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCTeamDAO --> select : parameters=" + URLQueryUtils.toString(parameters));

		List<Team> list = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(ConditionBuilder.parse(parameters, new TeamExpressionBuilder()));
		builder.append(" AND ");
		builder.append(COLUMN_DELETED + " = false");

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				list.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCTeamDAO --> select failed", "Teams not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return list;
	}

	@Override
	public List<Team> selectAll() throws DAOException {
		LOG.info("JDBCTeamDAO --> selectAll");

		List<Team> list = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_DELETED + " = false");

		Statement statement = null;

		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(builder.toString());
			while (resultSet.next()) {
				list.add(getResultSetValues(resultSet));
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCTeamDAO --> selectAll failed", "Teams not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return list;
	}
}

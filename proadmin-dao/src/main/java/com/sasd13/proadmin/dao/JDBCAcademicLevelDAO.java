/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sasd13.proadmin.dao;

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
import com.sasd13.proadmin.bean.AcademicLevel;
import com.sasd13.proadmin.util.dao.validator.ValidatorUtils;

/**
 *
 * @author Samir
 */
public class JDBCAcademicLevelDAO extends JDBCEntityDAO<AcademicLevel> implements IAcademicLevelDAO {

	private static final Logger LOG = Logger.getLogger(JDBCAcademicLevelDAO.class);

	@Override
	protected void editPreparedStatement(PreparedStatement preparedStatement, AcademicLevel academicLevel) throws SQLException {
		preparedStatement.setString(1, academicLevel.getCode());
	}

	@Override
	protected AcademicLevel getResultSetValues(ResultSet resultSet) throws SQLException {
		AcademicLevel academicLevel = new AcademicLevel(resultSet.getString(COLUMN_CODE));

		return academicLevel;
	}

	@Override
	public long insert(AcademicLevel academicLevel) throws DAOException {
		ValidatorUtils.validate(academicLevel);

		LOG.info("JDBCAcademicLevelDAO --> insert : code=" + academicLevel.getCode());

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
			editPreparedStatement(preparedStatement, academicLevel);

			preparedStatement.executeUpdate();

			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getLong(1);
			} else {
				throw new SQLException("Insert failed. No ID obtained");
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCAcademicLevelDAO --> insert failed", "AcademicLevel not inserted");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return id;
	}

	@Override
	public void update(AcademicLevel academicLevel) throws DAOException {
		LOG.info("JDBCAcademicLevelDAO --> update unavailable");
	}

	@Override
	public void delete(AcademicLevel academicLevel) throws DAOException {
		ValidatorUtils.validate(academicLevel);

		LOG.info("JDBCAcademicLevelDAO --> delete : code=" + academicLevel.getCode());

		StringBuilder builder = new StringBuilder();
		builder.append("DELTE FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(COLUMN_CODE + " = ?");

		PreparedStatement preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(builder.toString());
			preparedStatement.setString(1, academicLevel.getCode());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCAcademicLevelDAO --> delete failed", "AcademicLevel not deleted");
		} finally {
			doFinally(preparedStatement, LOG);
		}
	}

	@Override
	public AcademicLevel select(long id) throws DAOException {
		LOG.info("JDBCAcademicLevelDAO --> select : id=" + id);

		AcademicLevel academicLevel = null;

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
				academicLevel = getResultSetValues(resultSet);
			}
		} catch (SQLException e) {
			doCatch(e, LOG, "JDBCAcademicLevelDAO --> select failed", "AcademicLevel not readed");
		} finally {
			doFinally(preparedStatement, LOG);
		}

		return academicLevel;
	}

	public List<AcademicLevel> select(Map<String, String[]> parameters) throws DAOException {
		LOG.info("JDBCAcademicLevelDAO --> select : parameters=" + URLQueryUtils.toString(parameters));

		List<AcademicLevel> list = new ArrayList<>();

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT * FROM ");
		builder.append(TABLE);
		builder.append(" WHERE ");
		builder.append(ConditionBuilder.parse(parameters, new AcademicLevelExpressionBuilder()));
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
			doCatch(e, LOG, "JDBCAcademicLevelDAO --> select failed", "AcademicLevels not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return list;
	}

	@Override
	public List<AcademicLevel> selectAll() throws DAOException {
		LOG.info("JDBCAcademicLevelDAO --> selectAll");

		List<AcademicLevel> list = new ArrayList<>();

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
			doCatch(e, LOG, "JDBCAcademicLevelDAO --> selectAll failed", "AcademicLevels not readed");
		} finally {
			doFinally(statement, LOG);
		}

		return list;
	}
}
